package com.zillennium.utswap.utils

import android.webkit.WebViewClient
import com.zillennium.utswap.R
import com.zillennium.utswap.models.home.BannerObj


interface Constants {

    companion object {
        const val PREFERENCE_NAME = "UT_SWAP"
        const val DEVICE_TOKEN = "DEVICE_TOKEN"
        const val SETTING_INFO = "SETTING_INFO"
        const val LAST_PAYMENT_METHOD = "LAST_PAYMENT_METHOD"
        const val PAYMENT_AUTH_TOKEN = "PAYMENT_AUTH_TOKEN"
        const val PAYMENT_CHECK_OUT_RES = "PAYMENT_CHECK_OUT_RES"
        const val PAYMENT_ORDER = "PAYMENT_ORDER"
        const val PAYMENT_FROM_WEBVIEW = "PAYMENT_FROM_WEBVIEW"
        const val PAYMENT_PAGE_LOAD = "PAYMENT_PAGE_LOAD"
        const val PAYMENT_METHOD_LIST = "PAYMENT_METHOD_LIST"
        const val NOTIFICATION_OFFLINE_LIST = "NOTIFICATION_OFFLINE_LIST"

        const val DELAY: Long = 500 //MILI
    }

    interface FundPassword{
        companion object{
            const val SignIn = "sign in"
            const val ResetPassword = "reset password"
            const val Register = "register"
            const val ChangeLoginPassword = "change login password"
            const val forgotFundPassword = "forgot fund password"
            const val ForgotFundPassword = "Forgot Fund Password"
            const val ForgotLoginPassword = "Forgot Login Password"
            const val AddNumber = "add number"
            const val ChangeFundPassword = "Change Fund Password"
        }
    }

    interface HistoricalTransaction {
        companion object {
            const val MyTransactions = "My Transactions"
            const val Trade = "Trade"
            const val AllTransactions = "All Transactions"
        }
    }

    interface HistoricalMyTransactionIcon {
        companion object {
            const val Buy = R.drawable.ic_money_out
            const val Sell = R.drawable.ic_money_in
            const val SubScription = R.drawable.ic_hourglass
        }
    }

    /** Register data for resend code*/
    interface RegisterData {
        companion object {
            var username = ""
            var password = ""

        }
    }

    /** Add Phone Number for resend code*/
    interface AddPhoneNumber{
        companion object{
            var cellPhone = ""
        }
    }

    interface Key {
        companion object {
            const val ContentType = "Content-Type"
            const val Accept = "Accept"
            const val AcceptLanguage = "Accept-Language"
            const val Authorization = "Authorization"
            const val Token = "token"
            const val PartnerId = "partner-id"
            const val PartnerSecretKey = "partner-secret-key"
            const val profile = "PROFILE"
            const val ID = "id"
        }
    }

    interface Value {
        interface Network {
            companion object {
                const val MAX_RETRIES = 2
            }
        }

        companion object {
            const val ContentType = "application/json"
            const val Accept = "application/json"
            const val ContentTypeMultipart = "multipart/form-data"
        }
    }

    interface KeyValBookForVisit {
        companion object {
            const val date = "date"
            const val time = "time"
            const val displayDataObject = "display data object"
        }
    }

    interface KeyValMap {
        companion object {
            const val currentBoundary = "currentBoundary"
            const val countryBoundary = "countryBoundary"
            const val adminCode = "admin_code"
            const val subLevel = "sub_level"
            const val lat = "lat"
            const val lng = "lng"
            const val latitude = "latitude"
            const val longitude = "longitude"
            const val zoom_level = "zoom_level"
            const val switch_map = "switch_map"
            const val boundary = "boundary"
            const val coordinates = "coordinates"
            const val count = "count"
            const val point = "point"
            const val points = "points"
            const val radius = "radius"
            const val type = "type"
            const val at = "at"
            const val IN = "in"
            const val polygons = "polygons"
            const val drawing = "drawing"
        }
    }

    interface KeyAppShortCuts {
        companion object {
            const val shortcut_buy_sell = "link_bs_home"
            const val shortcut_indication_plus = "link_i_dashboard"
            const val shortcut_news = "news"
            const val short_news_new = "link_n_home"
            const val shortcut_rent = "rent_kh"
            const val shortcut_extra = "shortcut"
            const val shortcut_require_auth = "is_require_auth"
        }
    }

    interface Z1PaymentStatus {
        companion object {
            const val WAITING = "WAITING"
            const val SUCCESS = "SUCCESS"
            const val CLOSED = "CLOSED"
            const val WEBPAY = "WEBPAY"
            const val DEEPLINK = "DEEPLINK"
        }
    }

    interface APIPayment {
        companion object {
            const val onPaymentSuccessKey = "onPaymentSuccess"
            const val onPaymentError = "onPaymentError"
        }
    }

    interface IntentPayment {
        companion object {
            const val onActionFromReIndication = "onActionFromReIndication"
            const val onActionFromCreateCase = "onActionFromCreateCase"
            const val onActionNoShowDetails = "noShowDetails"
        }
    }

    interface HttpErrorCode {
        companion object {
            const val ERROR_AUTHENTICATION = WebViewClient.ERROR_AUTHENTICATION
            const val ERROR_TIMEOUT = WebViewClient.ERROR_TIMEOUT
            const val ERROR_TOO_MANY_REQUESTS = WebViewClient.ERROR_TOO_MANY_REQUESTS
            const val ERROR_UNKNOWN = WebViewClient.ERROR_UNKNOWN
            const val ERROR_BAD_URL = WebViewClient.ERROR_BAD_URL
            const val ERROR_CONNECT = WebViewClient.ERROR_CONNECT
            const val ERROR_FAILED_SSL_HANDSHAKE = WebViewClient.ERROR_FAILED_SSL_HANDSHAKE
            const val ERROR_HOST_LOOKUP = WebViewClient.ERROR_HOST_LOOKUP
            const val ERROR_PROXY_AUTHENTICATION = WebViewClient.ERROR_PROXY_AUTHENTICATION
            const val ERROR_REDIRECT_LOOP = WebViewClient.ERROR_REDIRECT_LOOP
            const val ERROR_UNSUPPORTED_AUTH_SCHEME =
                WebViewClient.ERROR_UNSUPPORTED_AUTH_SCHEME
            const val ERROR_FILE = WebViewClient.ERROR_FILE
            const val ERROR_FILE_NOT_FOUND = WebViewClient.ERROR_FILE_NOT_FOUND
            const val ERROR_IO = WebViewClient.ERROR_IO
        }
    }

    interface Tag {
        companion object {
            const val PAYMENT_LOG = "PAYMENT_LOG"
        }
    }

    interface KeyIntentRequestCode {
        companion object {
            const val REQUEST_CODE_FEEDBACK = 1
            const val REQUEST_CODE_SCRAPE_LAT_LNG = 1000
        }
    }

    interface KeyAuthTypeSignInSignUp {
        companion object {
            const val login = "login"
            const val register = "register"
            const val crm_user = "crm_user"
        }
    }

    interface KeyCaseTracking {
        companion object {
            const val you_submitted_the_case = "ic_case_submit"
            const val you_re_submitted_the_case = "ic_case_submit"
            const val case_is_being_worked_on = "ic_case_being_worked"
            const val finalizing_the_indication_price = "ic_case_finalizing"
            const val ready_to_download_the_report = "ic_case_ready_to_download"
            const val there_was_an_issue = "ic_case_issue"
        }
    }

    interface KeyAccessRoad {
        companion object {
            const val AT = "at"
            const val noAccessRoadFromMap = "no"
            const val noAccessRoadFromBackend = "No Road"
            const val IN = "in"
            const val MAX_MATCH = "max_match"
        }
    }

    interface KeyViewPdf {
        companion object {
            const val download = "Downloading"
            const val content = "content"
            const val prefix_file_name = "tempfile_"
            const val file_provider = ".provider"
            const val type_share_pdf = "application/pdf"
            const val pdf_path = "pdf path"
            const val mime_type = "application/pdf"
            const val mime_type_pdf = "application/pdf"
            const val mime_type_excel = "application/vnd.ms-excel"
        }
    }

    interface KeyUploadImg {
        companion object {
            const val ERROR_WHILE_UPLOAD =
                "Error while opening the image file. Please try again."
            const val NO_IMG = "No image"
            const val SELECT_PIC = "Select Picture"
            const val IMAGE_TYPE = "image/*"
            const val JPEG = "JPEG_"
            const val FILE_PROVIDER = ".provider"
            const val NOTHING_SELECT = "Nothing Selected"
            const val LIMIT_REACH = "Limit Reached!"
            const val PATH = "path"
            const val ACTION_CAMERA = "action-camera"
            const val ACTION_GALLERY = "action-gallery"
            const val IMAGE_PATH = "image-path"
            const val PICK_IMAGE_MULTIPLE = "action-multiple-image"
            const val ACTION = "action"
        }
    }

    interface KeyNotification {
        companion object {
            const val PostProperty = "post_property"
            const val FindProperty = "find_property"
            const val TakeATour = "take_a_tour"
            const val General = "General"
            const val Appraisal = "Appriasal"
            const val Lead = "Lead"
            const val Pending = "Pending"
            const val Closed = "Closed Won"
            const val NotificationFileName = "NotificationFile"
            const val News = "News"
            const val Indication_Plus = "Indication Plus"
            const val Buy_Sell = "Buy/Sell"
            const val ListingUpdate = "listing_update"
            const val KYC = "KYC"
            const val WEBVIEW = "webview"
            const val Rent = "Rent"
        }
    }

    interface KeyCurrentUseLandAndBuilding {
        companion object {
            const val TerracedHouse = "Terraced House (Flat House)"
            const val LinkedHouse = "Linked House"
            const val SemiDetachedHouse = "Semi-Detached House ( Twin Villa )"
            const val DetachedHouse = "Detached House ( Villa )"
            const val Dwelling = "Dwelling"
            const val Warehouse = "Warehouse"
            const val Factory = "Factory"
            const val GuestHouse = "Guest House"
            const val ShopHouse = "Shop House"
            const val Hotel = "Hotel"
            const val OfficeBuilding = "Office Building"
            const val GasolineStation = "Gasoline Station"
            const val ShoppingCenters = "Shopping Centers"
            const val Restaurant = "Restaurant"
            const val Resort = "Resort"
            const val RetailSpace = "Retail Space"
            const val ParkingFacilities = "Parking Facilities"
            const val MixedUseBuilding = "Mixed Use Building"
            const val EntertainmentBuilding = "Entertainment Building"
            const val PowerPlants = "Power Plants"
            const val School = "School"
            const val Hospital = "Hospital"
            const val GovernmentInstitution = "Government Institution"
        }
    }

    interface KeyUnitType {
        companion object {
            const val StudioUnit = "Studio Unit"
            const val OneBedroomUnit = "1-Bedroom Unit"
            const val TwoBedroomUnit = "2-Bedroom Unit"
            const val ThreeBedroomUnit = "3-Bedroom Unit"
            const val FourBedroomUnit = "4-Bedroom Unit"
            const val PenthhouseUnit = "Penthhouse Unit"
        }
    }

    interface KeyView {
        companion object {
            const val RiverSeaView = "River/Sea View"
            const val MountainView = "Mountain View"
            const val CityView = "City View"
            const val MultiView = "Multi-View"
            const val None = "None"
        }
    }

    interface KeyManagementFee {
        companion object {
            const val Fee = "Fee (excluded)"
            const val MoreThanOrEqualTo3 = "More than or equal to 3$ /sqm"
            const val LessThan1To3 = "Less than 1$ - 3$ /sqm"
        }
    }

    interface KeyPurpose {
        companion object {
            const val Sale = "Sale"
            const val LoanSecurity = "Loan Security"
            const val InternalManagement = "Internal Management"
        }
    }

    interface KeyShape {
        companion object {
            const val Rectangle = "Rectangle"
            const val Square = "Square"
            const val LShape = "L-Shape"
            const val Triangle = "Triangle"
            const val Irregular = "Irregular"
        }
    }

    interface KeyTopography {
        companion object {
            const val Level = "Level"
            const val Unlevelled = "Unlevelled"
            const val Unfilled = "Unfilled"
        }
    }

    interface KeySitePosition {
        companion object {
            const val CornerLot = "Corner Lot"
            const val IntermediateLot = "Intermediate Lot"
            const val EndLot = "End Lot"
        }
    }

    interface KeyPhysicalCondition {
        companion object {
            const val WellMaintenance = "Well-maintenance"
            const val Moderate = "Moderate"
            const val PoorMaintenance = "Poor-maintenance"
        }
    }

    interface KeyTitleDeedType {
        companion object {
            const val LongTermLease = "Long-Term Lease"
            const val SoftTitleDeed = "Soft Title Deed"
            const val HardTitleDeed = "Hard Title Deed"
        }
    }

    interface KeyFloorNumber {
        companion object {
            const val E0 = "E0. House"
            const val E1 = "E1. House"
            const val E2 = "E2. House"
            const val E3 = "E3. House"
            const val E4 = "E4. House"
            const val E5 = "E5. House"
            const val E6 = "E6. House"
            const val Rooftop = "Rooftop House"
        }
    }

    interface KeyCaseStatus {
        companion object {
            const val New = "New"
            const val AllCases = "All Cases"
            const val Inactive = "Inactive"
            const val Progress = "Progress"
            const val Pending = "Pending"
            const val ClosedWon = "Closed Won"
            const val FinalDraft = "Final Draft"
            const val Escalated = "Escalated"
            const val SoldOut = "Sold Out"
        }
    }

    interface KeyDashBoardIndicationPlus {
        companion object {
            const val TotalCase = "Total Cases"
            const val Land = "Land"
            const val Building = "Building"
            const val LandAndBuilding = "Land and Building"
        }
    }

    interface KeyCreateCase {
        companion object {
            const val instantStatus = "instant status"
            const val submitCaseSaveDraft = "submit_case_save_draft"
        }
    }

    interface NearbyType {
        companion object {
            const val AUTOMOBILES = "automobiles"
            const val EDUCATION = "education"
            const val FINANCE_AND_BANKING = "finance_and_banking"
            const val FOOD_AND_BEVERAGES = "food_and_beverages"
            const val HOME_IMPROVEMENT = "home_improvement"
            const val LANDMARK = "landmark"
            const val LIFE_STYLE = "life_style"
            const val MEDICAL_SERVICES = "medical_services"
            const val RELIGION = "religion"
            const val TRANSPORTATION = "transportation"
            const val OTHER_SERVICES = "other_services"
        }
    }

    interface KeyBuySellFilter {
        companion object {
            const val Location = "location"
            const val Price = "price"
            const val Size = "size"
            const val Sort = "sort"
            const val priceSqm = "price_sqm"
            const val totalPrice = "total_price"
        }
    }

    interface TradeExchange {
        companion object {
            var ProjectName = "ProjectName"
            var Status_submit = "KYC"
            var ProjectNameToolBar = "ProjectName_toolBar"
            var MarketId = "MarketId"
            var MarketName="MarketName"
            var ProjectId = "ProjectId"
            var availableBalance = ""
            var utBalance = ""
            var errorMessagePlaceOrder = ""
            var sellFee = ""
            var buyFee = ""
        }
    }
    interface Project{
        companion object{
            const val ProjectName = "Project_Name"
            const val Project_Id = "id"
        }
    }
    interface Deposit{
        companion object{
            const val Payment_Link ="payment_link"
            const val  TRANSATION_ID= "transaction_id"
            const val  TOTAL_BALANCE= "Total_balance"
        }
    }

    interface Transfer{
        companion object{
            var amount = ""
            var trxTransfer = ""
            var trxDate = ""
            var fromAccount = ""
            var toAccount = ""
        }
    }

    interface TransferFundPassword{
        companion object{
            const val transfer = "Transfer"
            const val withdraw = "Withdraw"
            const val subscription = "Subscription"
        }
    }

    /** User Balance */
    interface UserBalance {
        companion object {
            const val All = ""
            const val Deposit = "deposit"
            const val Withdrawal = "withdraw"
            const val Transfer = "transfer"
            const val Trading = "trade"
            const val Subscriptions = "subsciption"
        }
    }

    interface UserBalanceIcon {
        companion object {
            const val BalanceDeposit = R.drawable.ic_balance_deposit
            const val BalanceWithdrawal = R.drawable.ic_balance_withdraw
            const val BalanceTransfer = R.drawable.ic_transfer
            const val BalanceTrading = R.drawable.ic_trade
            const val BalanceSubscriptions = R.drawable.ic_hourglass
            const val BalanceTradeBuy = R.drawable.ic_money_out
            const val BalanceTradeSell = R.drawable.ic_money_in
        }
    }

    interface OrderBookTable{
        companion object{
            var marketNameOrderBook = ""
            var marketIdChart =""
            var projectName = ""
        }
    }

    interface WatchList{
        companion object{
            var itemWatchList = ArrayList<BannerObj.ItemWishList>()

        }
    }

    interface PortfolioFilter {
        companion object{
            const val Change = "Change"
            const val Performance = "Performance"
            const val Price = "Price"
            const val Balance = "Balance"
            const val Weight = "Weight"
        }
    }

}