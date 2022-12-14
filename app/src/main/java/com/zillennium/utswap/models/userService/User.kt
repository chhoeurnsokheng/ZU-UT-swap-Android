package com.zillennium.utswap.models.userService

object User {

    /**Login Response*/
    class LoginRes {
        var status: Int? = null
        var message: String? = null
        var data: LoginData? = null
    }

    class LoginData {
        var ID: String? = null
        var TOKEN: String? = null
        var x_api_key: String? = null
        var fundpass: Int? = null
        var status_kyc: Boolean? =null
        var status_submit_kyc:Boolean?=null
    }

    class LoginObject(
        var username: String?,
        var password: String?
    )

    /**OTP Code*/
    class OtpRes {
        var status: Int? = null
        var message: String? = null
        var data: OtpData? = null
    }

    class OtpData {
        var ID: String? = null
        var TOKEN: String? = null
        var x_api_key: String? = null
        var fundpass: Int? = null
    }

    class OtpObject(
        var otp_code: String?,
        var secure_key: String?
    )

    class KycList {
        var truename = ""
        var gender = ""
        var occupation = ""
        var companyname = ""
        var email = ""
        var citycode = ""
        var districtcode = ""
        var communecode = ""
        var streetnumber = ""
        var idcardinfo = ""
        var idcardfront = ""
        var idcardrear = ""
        var userImage = ""
        var idcard = ""
        var termandcondition = ""
        var paypassword = ""
        var repaypassword = ""
    }

    class KycRes {
        var status: String = " "
        var message:String =""
        var data: ItemRes? = null
    }

    class KYCStatus {
        var status: String = " "
        var message:String =""
        var data: ItemRes? = null

    }

    class ItemRes {

        var UserID: String = ""
        var status_kyc: Boolean = false
        var status_submit_kyc: Boolean = false

        var status_kyc_submit:Boolean? = null
        var status_kyc_approved:Boolean? = null
    }

    class Kyc(
        var truename: String = "",
        var gender: String = "",
        var occupation: String = "",
        var companyname: String = "",
        var email: String = "",
        var phonenumber: String = "",
        var cellphones: String = "",
        var citycode: String = "",
        var districtcode: String = "",
        var communecode: String = "",
        var streetnumber: String = "",
        var idcardinfo: String = "",
        var idcardfront: String = "",
        var idcardrear: String = "",
        var userImage: String = "",
        var idcard: String = "",
        var termandcondition: String = "",
        var paypassword: String = "",
        var repaypassword: String = ""
    )

    /** Register */
    class RegisterRes {
        var status: Int? = null
        var message: String? = null
        var data: RegisterData? = null
    }

    class RegisterData {
        var secure_key: String? = null
    }

    class RegisterObject(
        var username: String?,
        var password: String?,
        var verify: String?,
        var invit: String?
    )

    /** Forgot Password*/
    class ForgotPasswordRes{
        var status: Int? = null
        var message: String? = null
        var data: ForgotPasswordData? = null
    }

    class ForgotPasswordData{
        var secure_key: String? = null
    }

    class ForgotPasswordObject(
        var username: String?
    )

    /** Forgot Password Verify*/
    class ForgotPasswordVerifyRes{
        var status: Int? = null
        var message: String? = null
        var data: ForgotPasswordVerifyData? = null
    }

    class ForgotPasswordVerifyData{
        var secure_key: String? = null
    }

    class ForgotPasswordVerifyObject(
        var otp_code: String?,
        var secure_key: String?
    )

    /** Enter New Password*/
    class EnterNewPasswordObject(
        var secure_key: String?,
        var new_password: String?,
        var verify_password: String?
    )

    class EnterNewPasswordRes{
        var status: Int? = null
        var message: String? = null
        var data: EnterNewPasswordData? = null
    }

    class EnterNewPasswordData{

    }

    /** Change Login Password*/
    class ChangeLoginPasswordRes{
        var status: Int? = null
        var message: String? = null
        var data: Any? = null
    }

    class ChangeLoginPasswordObject(
        var old_password: String?,
        var new_password: String?,
        var verify_password: String?
    )

    /** Change Fund Password*/
    class CheckOldFundPasswordRes{
        var status: Int? = null
        var message: String? = null
        var data: Any? = null
    }

    class CheckOldFundPasswordObject(
        var old_fund: String?
    )

    class ChangeFundPasswordRes{
        var status: Int? = null
        var message: String? = null
        var data: Any? = null
    }

    class ChangeFundPasswordObject(
        var new_fund: String?,
        var verify_fund: String?
    )

    /** Add Phone Number*/
    class AddPhoneNumberRes{
        var status: Int? = null
        var message: String? = null
        var data: AddPhoneNumberData? = null
    }

    class AddPhoneNumberData{
        var secure_key: String? = null
    }

    class AddPhoneNumberObject(
        var cellphone: String?
    )

    /** Verify Add phone number*/
    class VerifyAddPhoneNumberRes{
        var status: Int? = null
        var message: String? = null
        var data: VerifyAddPhoneNumberData? = null
    }

    class VerifyAddPhoneNumberData{
        var phone: String? = null
    }

    class VerifyAddPhoneNumberObject(
        var secure_key: String?,
        var otp_code: String?
    )

    /** App Side Bar*/
    class AppSideBarRes{
        var status: Int? = null
        var message: String? = null
        var data: AppSideBarData? = null
    }

    class AppSideBarData{
        var username: String? = null
        var truename: String? = null
        var kyc: String? = null
        var email: String? = null
        var phonenumber: String? = null
        var ocupation: String? = null
        var company_name: String? = null
        var address: String? = null
        var image_profile: String? = null
        var mobile: String? = null
        var image_lavel: String? = null
        var name_user_lavel: String? = null
        var doc_user_lavel: AppSideBarDocUserLevel? = null
    }

    class AppSideBarDocUserLevel{
        var id : String? = null
        var title: String? = null
        var criteria: List<CriteriaList>? = null
        var priority_and_privileges: List<PriorityAndPrivilegesList>? = null
    }

    class CriteriaList{
        var id: Int? = null
        var title: String? = null
        var content: String? = null
    }

    class PriorityAndPrivilegesList{
        var id: Int? = null
        var title: String? = null
        var content: String? = null
    }

    /** Check User Login Status*/
    class CheckUserLoginStatusRes{
        var status: Int? = null
        var message: String? = null
        var data: Any? = null
    }

    class CheckUserLoginStatusData{
        var user_id: Int? = null
    }

    /** Account Upload Profile*/
    class AccountUploadProfileObject(
        var image: String?
    )

    class AccountUploadProfileRes{
        var status: Int? = null
        var message: String? = null
        var data: AccountUploadProfileData? = null
    }

    class AccountUploadProfileData{
        var path_image: String? = null
    }
}