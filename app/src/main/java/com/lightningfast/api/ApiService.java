package com.lightningfast.api;

import com.lightningfast.base.HttpResult;
import com.lightningfast.bean.ApplyBean;
import com.lightningfast.bean.AuditingBean;
import com.lightningfast.bean.BannerBean;
import com.lightningfast.bean.BaseBean;
import com.lightningfast.bean.CompanyDataBean;
import com.lightningfast.bean.ContactBean;
import com.lightningfast.bean.DatasBean;
import com.lightningfast.bean.EmployeeBean;
import com.lightningfast.bean.EmployeeDataBean;
import com.lightningfast.bean.EmployeeLoginBean;
import com.lightningfast.bean.HomeMessageBean;
import com.lightningfast.bean.ImageBean;
import com.lightningfast.bean.LoanBean;
import com.lightningfast.bean.LoginBean;
import com.lightningfast.bean.MoneyBean;
import com.lightningfast.bean.MyLoanBean;
import com.lightningfast.bean.MyLoanTotalBean;
import com.lightningfast.bean.NoticeBean;
import com.lightningfast.bean.OverdueBean;
import com.lightningfast.bean.PersonDataBean;
import com.lightningfast.bean.PersonInfoBean;
import com.lightningfast.bean.QuotaBean;
import com.lightningfast.bean.RegisterBean;
import com.lightningfast.bean.RepaymentBean;
import com.lightningfast.bean.RepaymentDetailsBean;
import com.lightningfast.bean.RepaymentResultBean;
import com.lightningfast.bean.StagesBean;
import com.lightningfast.bean.TokenBean;
import com.lightningfast.bean.TypeBean;
import com.lightningfast.bean.VersionBean;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 接口
 */

public interface ApiService {
    String STATUS_SUC = "1";
    String TOKEN_EX = "2";
//        String BASE_URL = "http://dev.sdkfenqi.com/";//测试服务器
    String BASE_URL = "https://www.sdkfenqi.com/";//正式服务器
//    String BASE_URL = "http://192.168.1.186/";
    /***
     * 请求popwindows填充数据传的参数
     * */
    String popwindows = "1,2,3,4,5,6,7";

    /***
     * 检测当前账号是否注册
     * */
    @POST("api/login/check_customer_exist")
    @FormUrlEncoded
    Observable<HttpResult<String>> checkMobile(@Field("mobile") String mobile);

    /****
     * 注册用户
     * */
    @POST("api/login/regist")
    @FormUrlEncoded
    Observable<RegisterBean> register(@Field("mobile") String mobile, @Field("captcha") String captcha
            , @Field("password") String password);

    /**
     * 登录
     */
    @POST("api/login/login")
    @FormUrlEncoded
    Observable<LoginBean> login(@Field("mobile") String mobile, @Field("password") String password);

    /****
     * 获取短信验证接口
     * */
    @POST("api/login/create_verify")
    @FormUrlEncoded
    Observable<BaseBean> createVerify(@Field("mobile") String mobile);

    /***
     * 获取个人信息
     * */
    @POST("api/customer/get_customer_info")
    @FormUrlEncoded
    Observable<PersonDataBean> getPersonData(@Field("cid") String cid, @Field("token") String token);

    /***
     * 添加联系人信息
     * */
    @POST("api/customer/update_contact_info")
    @FormUrlEncoded
    Observable<BaseBean> addContantsInfomations(@Field("id") String id
            , @Field("cid") String cid
            , @Field("contact_name") String contact_name
            , @Field("guanxi") String guanxi
            , @Field("mobile") String mobile
            , @Field("type") String type
            , @Field("token") String token);

    /****
     * 添加所在公司信息
     * */
    @POST("api/customer/update_company_info")
    @FormUrlEncoded
    Observable<BaseBean> addCompanyInfo(
            @Field("company_id") String company_id
            , @Field("cid") String cid
            , @Field("province_id") String province_id
            , @Field("city_id") String city_id
            , @Field("area_id") String area_id
            , @Field("company_address") String company_address
            , @Field("company_name") String company_name
            , @Field("company_nature") String company_nature
            , @Field("work_years") String work_years
            , @Field("salary") String salary
            , @Field("contact_name") String contact_name
            , @Field("mobile") String mobile
            , @Field("contact_id") String contact_id
            , @Field("token") String token);

    /****
     * 获取用户所在公司信息
     * */
    @POST("api/customer/get_company_info")
    @FormUrlEncoded
    Observable<CompanyDataBean> updateCompanyInfo(@Field("cid") String cid, @Field("token") String token);

    /****
     * 找回密码
     * */
    @POST("api/login/reset_password")
    @FormUrlEncoded
    Observable<BaseBean> resetPassWord(@Field("mobile") String mobile
            , @Field("captcha") String captcha, @Field("password") String password);

    /***
     * 获取分期类型
     * */
    @POST("api/index/get_fenqi_type")
    @FormUrlEncoded
    Observable<TypeBean> updateType(@Field("") String aa);

    /***
     * 轮播图
     * */
    @POST("api/index/get_index_ad_img")
    @FormUrlEncoded
    Observable<BannerBean> updateBanner(@Field("a") String aa);

    /***
     * 分期时间
     * **/
    @POST("api/index/get_fenqi_num_by_type")
    @FormUrlEncoded
    Observable<StagesBean> updateStages(@Field("fenqi_type_id") String fenqi_type_id);

    /****
     * 图片上传
     * */
    @POST("api/fileupload/file_upload")
    @Multipart
    Observable<BaseBean> uploadImage(@Part MultipartBody.Part body
            , @Part("file_sign") RequestBody file_sign
            , @Part("customer_id") RequestBody customer_id
            , @Part("token") RequestBody token);

    /***
     * 首页滚动消息
     * */
    @POST("api/customer_order/orderMessage")
    @FormUrlEncoded
    Observable<HomeMessageBean> updateHomePageMessage(@Field("a") String aaa);

    /***
     * 添加用户基本信息
     * */
    @POST("api/customer/update_customer_info")
    @FormUrlEncoded
    Observable<BaseBean> updatePersonInfomation(
            @Field("cid") String customer_id
            , @Field("customer_name") String customerName
            , @Field("gender") String gender
            , @Field("card_address") String card_address
            , @Field("id_card") String id_card
            , @Field("bank") String bank
            , @Field("bank_number") String bank_number
            , @Field("token") String token
            , @Field("cert_expire") String card_end_date
            , @Field("bank_telephone_no") String bank_telephone_no
            , @Field("bank_subbranch") String bank_subbranch
            , @Field("bank_city") String bank_city);

    /****
     * 获取popwindow的填充数据
     * */
    @POST("api/customer/getAllWorkbook")
    @FormUrlEncoded
    Observable<DatasBean> getPopwindowsData(@Field("type") String type);

    /****
     * 联系人信息-获取
     * */
    @POST("api/customer/get_contact_info")
    @FormUrlEncoded
    Observable<ContactBean> getContactInfo(@Field("cid") String cid, @Field("type") String type
            , @Field("token") String token);

    /****
     * 申请借款-最小额度 和最大额度
     * */
    @POST("api/customer_order/applicationQuota")
    @FormUrlEncoded
    Observable<QuotaBean> getQuotaInfo(@Field("a") String aa);

    /****
     *添加基本信息
     * */
    @POST("api/customer/updateCustomerBasic")
    @FormUrlEncoded
    Observable<BaseBean> addPersonData(@Field("cid") String cid
            , @Field("education") String education, @Field("marry") String marry
            , @Field("residence") String residence, @Field("is_car") String is_car
            , @Field("house_nature") String house_nature, @Field("provice_id") String provice_id
            , @Field("city_id") String city_id, @Field("area_id") String area_id
            , @Field("live_address") String live_address
            , @Field("token") String token);

    /****
     * 获取用户信息
     * */
    @POST("api/customer/getCustomerBasic")
    @FormUrlEncoded
    Observable<PersonInfoBean> getPersonBaseData(@Field("cid") String cid, @Field("token") String token);

    /***
     * 申请借款-提交借款
     * */
    @POST("api/customer_order/updatesLoan")
    @FormUrlEncoded
    Observable<BaseBean> updatesLoan(@Field("cid") String cid, @Field("fenqi_type_id") String fenqi_type_id
            , @Field("type_detail_id") String type_detail_id, @Field("price") String price
            , @Field("token") String token);

    /*****
     * 通讯录上传
     * */
    @POST("api/customer_tel_book/addTelBook")
    @FormUrlEncoded
    Observable<BaseBean> updateTelBook(@Field("cid") String cid, @Field("tel_str") String tel_str, @Field("token") String token);

    /****
     * 我的借款-
     * */
    @POST("api/customer_order/getMyOrderList")
    @FormUrlEncoded
    Observable<MyLoanBean> updateMyorder(@Field("customer_id") String customer_id
            , @Field("token") String token
            , @Field("page") int page);

    /***
     * 我的借款  借款金额  还款金额
     * */
    @POST("api/customer_order/getmyorder")
    @FormUrlEncoded
    Observable<MyLoanTotalBean> updateMyLoanTotal(@Field("customer_id") String customer_id
            , @Field("token") String token);

    /****
     * 获取TOken
     * */

    @POST("api/login/getToken")
    @FormUrlEncoded
    Observable<TokenBean> updateToken(@Field("cid") String cid);

    /****
     * 获取证件照
     * */
    @POST("api/fileupload/getPicture")
    @FormUrlEncoded
    Observable<ImageBean> updateImage(@Field("customer_id") String customer_id, @Field("token") String token);

    /****
     * 待还款
     * */
    @POST("api/customer_order/myRepayment")
    @FormUrlEncoded
    Observable<RepaymentBean> updateRepayment(@Field("customer_id") String customer_id);

    /****
     * 是否可以借款
     * */
    @POST("api/customer_order/isLoan")
    @FormUrlEncoded
    Observable<BaseBean> getIsLoan(@Field("customer_id") String customer_id, @Field("token") String token);

    /*****
     * 订单详情
     * */
    @POST("api/customer_repayment/getMyRepaymentLog")
    @FormUrlEncoded
    Observable<RepaymentDetailsBean> getRepaymentDetails(@Field("customer_id") String customer_id
            , @Field("order_id") String order_id, @Field("token") String token);

    /***
     * 还款每一期的钱数
     * **/
    @POST("api/customer_repayment/getRepaymentPrice")
    @FormUrlEncoded
    Observable<MoneyBean> getMoneyData(@Field("order_id") String order_id
            , @Field("fenqi_nums") String fenqi_nums
            , @Field("token") String token);

    /****
     * 获取业务员列表
     * */
    @POST("api/employee/getEmployee")
    @FormUrlEncoded
    Observable<EmployeeBean> getEmployee(@Field("a") String aa);

    /****
     * 添加业务员
     * */
    @POST("api/customer/addEmployee")
    @FormUrlEncoded
    Observable<BaseBean> addEmployee(@Field("customer_id") String customer_id
            , @Field("employee_id") String employee_id);

    /***
     * 确认还款
     * */
    @POST("api/customer_repayment/sureRepaymentPrice")
    @FormUrlEncoded
    Observable<BaseBean> confirmRepayment(@Field("order_id") String order_id
            , @Field("fenqi_nums") String fenqi_nums
            , @Field("need_price") String need_price
            , @Field("customer_id") String customer_id
            , @Field("token") String token);

    /****
     * 上传通话记录
     * */
    @POST("api/customer_tel_book/communication")
    @FormUrlEncoded
    Observable<BaseBean> updateCallLog(@Field("cid") String cid, @Field("tel_str") String tel_str, @Field("token") String token);

    /***
     * 还款检测
     * */
    @POST("api/customer_repayment/isFinish")
    @FormUrlEncoded
    Observable<RepaymentResultBean> getRepaymentResult(@Field("customer_id") String customer_id
            , @Field("token") String token, @Field("order_id") String order_id);

    /****
     * 消息列表
     * */
    @POST("api/message/index")
    @FormUrlEncoded
    Observable<NoticeBean> updateNoticeList(@Field("customer_id") String customer_id
            , @Field("token") String token, @Field("page") String page
            , @Field("rows") String rows);

    /****
     *点击已读
     * */
    @POST("api/message/detail")
    @FormUrlEncoded
    Observable<BaseBean> updateIsRead(@Field("customer_id") String customer_id
            , @Field("message_id") String message_id, @Field("token") String token);

    /****
     * 贷款进度
     * */
    @POST("api/customer_order/auditing")
    @FormUrlEncoded
    Observable<AuditingBean> updateaAuditing(@Field("cid") String cid, @Field("token") String token);

    /**------------------------------------------业务经理--------------------------------------------------------* */

    /*****
     * 员工和老板登录接口
     * */
    @POST("employee/login/login")
    @FormUrlEncoded
    Observable<EmployeeLoginBean> employeeLogin(@Field("mobile") String mobile, @Field("password") String password);

    /***
     * 正在还款客户
     * */
    @POST("employee/employee/getCustomerRepayList")
    @FormUrlEncoded
    Observable<LoanBean> updateCustomerRepayList(@Field("id") String id
            , @Field("token") String token, @Field("page") String page);

    /****
     * 申请贷款的客户
     * */
    @POST("employee/employee/getCustomerApplyList")
    @FormUrlEncoded
    Observable<ApplyBean> updateCustomerApplyList(@Field("id") String id
            , @Field("token") String token, @Field("page") String page);

    /***
     * 逾期客户列表
     * **/
    @POST("employee/employee/overdueOrderList")
    @FormUrlEncoded
    Observable<OverdueBean> updateoverdueOrderList(@Field("id") String id
            , @Field("token") String token, @Field("page") String page);

    /****
     *业务员信息
     * */
    @POST("employee/employee/getEmployeeInfo")
    @FormUrlEncoded
    Observable<EmployeeDataBean> updateEmployeeInfo(@Field("id") String id, @Field("token") String token);

    /****
     *修改密码（业务员）
     * */
    @POST("employee/login/resetPassword")
    @FormUrlEncoded
    Observable<BaseBean> employeeResetPassword(@Field("id") String id
            , @Field("password") String password, @Field("new_password") String new_password);

    /***
     * 上传图片
     * **/
    @POST("employee/picture/file_upload")
    @Multipart
    Observable<BaseBean> updatePictures(@PartMap Map<String, RequestBody> bodyMap, @QueryMap Map<String, String> map);

    /****
     * 版本更新
     * */
    @GET("api/Devlog/checkVersion")
    Observable<VersionBean>updateCheckVersion();
}
