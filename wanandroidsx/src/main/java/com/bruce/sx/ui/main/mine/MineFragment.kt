package com.bruce.sx.ui.main.mine

import android.os.Bundle
import android.view.View
import com.bruce.sx.R
import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.LazyFragment
import com.bruce.sx.constants.Constants
import com.bruce.sx.entity.IntegralEntity
import com.bruce.sx.event.LoginEvent
import com.bruce.sx.event.LogoutEvent
import com.bruce.sx.ui.Integral.IntegralActivity
import com.bruce.sx.ui.collect.CollectActivity
import com.bruce.sx.ui.girl.GirlActivity
import com.bruce.sx.ui.login.LoginActivity
import com.bruce.sx.ui.my.MyArticleActivity
import com.bruce.sx.ui.rank.RankActivity
import com.bruce.sx.ui.set.SetActivity
import com.bruce.sx.ui.web.WebActivity
import com.bruce.sx.utils.AppManager
import com.bruce.sx.utils.PrefUtils
import com.bruce.sx.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_mine.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author: brcue
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.mine
 * @description:个人信息
 * @date: 2020/3/26
 * @time:  16:38
 */
class MineFragment : LazyFragment<MineContract.Presenter<MineContract.View>>(),View.OnClickListener,
    MineContract.View {
    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun closeLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var integralEntity: IntegralEntity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun lazyInit() {
        //先判断数据是否为空，然后再强转，否则会出异常
        PrefUtils.getObject(Constants.INTEGRAL_INFO)?.let {
            //先从本地获取积分，获取不到再通过网络获取
            integralEntity = it as IntegralEntity?
        }
        if (integralEntity==null){
            if (AppManager.isLogin()) {
                //信息
                presenter?.loadIntegral()
            }
        }else{
            setIntegral()
        }
        setListener()
    }

    private fun setIntegral(){
        integralEntity?.apply {
            tvUserName.text = username
            tvId.text = String.format("%s","id:$userId")
            tvRanking.text = rank.toString()
            tvIntegral.text = coinCount.toString()
        }
    }

    private fun setListener(){
        ivHead.setOnClickListener(this)
        tvUserName.setOnClickListener(this)
        llHistory.setOnClickListener(this)
        llRanking.setOnClickListener(this)
        rlIntegral.setOnClickListener(this)
        rlCollect.setOnClickListener(this)
        rlIntegral.setOnClickListener(this)
        rlArticle.setOnClickListener(this)
        rlWebsite.setOnClickListener(this)
        rlGirl.setOnClickListener(this)
        rlSet.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            //头像
            R.id.ivHead->ToastUtils.show("我只是一只可爱的小老鼠..")
            //用户名
            R.id.tvUserName->{
                if (!AppManager.isLogin()){
                    intent(LoginActivity::class.java,false)
                }
            }
            //足迹
            R.id.llHistory->ToastUtils.show("正在开发中...")
            //排名
            R.id.llRanking->{
                intent(Bundle().apply {
                    integralEntity?.coinCount?.let { putInt(Constants.MY_INTEGRAL, it) }
                    integralEntity?.rank?.let { putInt(Constants.MY_RANK, it) }
                    integralEntity?.username?.let { putString(Constants.MY_NAME, it) }
                }, RankActivity::class.java,true)
            }
            //积分
            R.id.rlIntegral->intent(IntegralActivity::class.java,true)
            //收藏
            R.id.rlCollect-> intent(CollectActivity::class.java,true)
            //我的文章
            R.id.rlArticle-> intent(MyArticleActivity::class.java,true)
            //官网
            R.id.rlWebsite->{
                intent(Bundle().apply {
                    putString(Constants.WEB_URL,Constants.WEBSITE)
                    putString(Constants.WEB_TITLE,Constants.APP_NAME)
                }, WebActivity::class.java,false)
            }
            //轻松一下
            R.id.rlGirl->intent(GirlActivity::class.java,false)
            //设置
            R.id.rlSet-> intent(SetActivity::class.java,false)
        }
    }

    override fun showIntegral(e: IntegralEntity) {
        this.integralEntity = e
        setIntegral()
    }
    override fun onError(error: String) {
        ToastUtils.show(error)
    }

    override fun createPresenter(): MineContract.Presenter<MineContract.View>? {
        return MinePresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun onDestroy() {
        super.onDestroy()
        //设置积分为空，下次进入界面重新请求
        PrefUtils.removeKey(Constants.INTEGRAL_INFO)
        EventBus.getDefault().unregister(this)
    }

    /**
     * 登陆消息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun loginEvent(loginEvent: LoginEvent){
        presenter?.loadIntegral()
    }

    /**
     * 退出消息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun logoutEvent(loginEvent: LogoutEvent){
        tvUserName.text = "请先登录"
        tvId.text = "--"
        tvRanking.text = "0"
        tvIntegral.text = "0"
    }
}