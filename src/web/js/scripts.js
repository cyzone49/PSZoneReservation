var username = null;
var password = null;
var userID = null;
var email = null;
var phone = null;

var reservations = [];
var res = null
var errorMsg = null


function submit()
{
    // var handle = document.getElementById("handleBox").value;
    var handle = "/command";
    var requestBody = document.getElementById("requestBox").value;
    // var token = document.getElementById("authToken").value;

    var method = "post";

    send(handle,requestBody, method);
    return false;
}

function send(path, params, method, token)
{
    var obj = new XMLHttpRequest();
    obj.onreadystatechange = function()
    {
        var response = obj.responseText;
        document.getElementById("response").value = formatJson(response);
        accessResponse(response);

    };
    obj.open(method,path,false);
    obj.setRequestHeader("Content-Type", "application/json");
    // obj.setRequestHeader("Authorization", token);
    obj.send(params);
}

function formatJson(inputText)
{
    var temp = "";
    var indent = 0;
    for(var i in inputText)
    {
        var char = inputText[i];
        if(char != null)
        {
            if(char === ']' || char === '}')
            {
                temp += "\n";
                indent--;
                for(var j = 0; j < indent; j++)
                {
                    temp += '\t';
                }

            }

            temp += char;

            if (char === ',')
            {
                temp += "\n";

                for(j = 0; j < indent; j++)
                {
                    temp += '\t';
                }

            }
            if(char === '{' || char === '[')
            {
                temp += "\n";
                indent++;
                for(j = 0; j < indent; j++)
                {
                    temp += '\t';
                }
            }
        }
    }

    return temp;
}

function hideInputBoxes() {
    var x = document.getElementsByClassName("inputbox");
    var i;
    for (i = 0; i < x.length; i++) {
        x[i].style.display = 'none';
    }
}

function accessResponse(r) {
    res = JSON.parse(r)

    var resultData = res.resultData
    var success = res.success
    var commandType = resultData.commandType

    if(success == true) {

        if(commandType === "LOGIN") {
            username = resultData.username
            password =  resultData.password
            email = resultData.email
            phone = resultData.phone
            userID = resultData.userID
        }
        else if(commandType === "MAKE_RESERVATION") {
            reservations.push(resultData.confirmationID)
        }
        else if(commandType === "CANCEL_RESERVATION") {
            removeReservation(reservations, resultData.confirmationID)
        }

    } else {
        errorMsg = resultData.msg
    }
}

function removeReservation(arr) {
    var what, a = arguments, L = a.length, ax;
    while (L > 1 && arr.length) {
        what = a[--L];
        while ((ax= arr.indexOf(what)) !== -1) {
            arr.splice(ax, 1);
        }
    }
    return arr;
}

function login()
{
    window.scrollTo(0,document.body.scrollHeight);
    document.getElementById("handleBox").value = "LOGIN";
    var uname = document.getElementById("login-username").value;
    var pw = document.getElementById("login-password").value;
    document.getElementById("requestBox").value = formatJson("{\"commandType\":\"LOGIN\"," +
        "\"username\":\"" + uname +
        "\",\"password\":\"" + pw + "\"}");
    hideInputBoxes()
    document.getElementById("inputbox-login").style.display = "block";

}
function register()
{
    window.scrollTo(0,document.body.scrollHeight);
    document.getElementById("handleBox").value = "REGISTER";
    var uname = document.getElementById("register-username").value;
    var pw = document.getElementById("register-password").value;
    var p = document.getElementById("register-phone").value;
    var e = document.getElementById("register-email").value;
    document.getElementById("requestBox").value = formatJson("{\"commandType\":\"REGISTER\"," +
        "\"username\":\"" + uname +
        "\",\"password\":\"" + pw +
        "\",\"email\":\"" + e +
        "\",\"phone\":\"" + p + "\"}");
    hideInputBoxes()
    document.getElementById("inputbox-register").style.display = "block";

}
function addLocation()
{
    window.scrollTo(0,document.body.scrollHeight);
    document.getElementById("handleBox").value = "ADD_LOCATION";
    document.getElementById("requestBox").value = "";
    hideInputBoxes()
    document.getElementById("inputbox-add-location").style.display = "block";
}
function getLocation()
{
    window.scrollTo(0,document.body.scrollHeight);
    document.getElementById("handleBox").value = "GET_LOCATIONS";
    document.getElementById("requestBox").value = "";
    hideInputBoxes()
}
function addPackage()
{
    window.scrollTo(0,document.body.scrollHeight);
    document.getElementById("handleBox").value = "ADD_PACKAGE";
    document.getElementById("requestBox").value = "";
    hideInputBoxes()
    document.getElementById("inputbox-add-package").style.display = "block";
}
function getPackage()
{
    window.scrollTo(0,document.body.scrollHeight);
    document.getElementById("handleBox").value = "GET_PACKAGES";
    document.getElementById("requestBox").value = "";
}
function addSlot()
{
    window.scrollTo(0,document.body.scrollHeight);
    document.getElementById("handleBox").value = "ADD_SLOT";
    document.getElementById("requestBox").value = "";
    hideInputBoxes()
    document.getElementById("inputbox-add-slot").style.display = "block";
}
function getAvailableSlots()
{
    window.scrollTo(0,document.body.scrollHeight);
    document.getElementById("handleBox").value = "GET_AVAILABLE_SLOTS";
    document.getElementById("requestBox").value = "";
}
function makeReservation()
{
    window.scrollTo(0,document.body.scrollHeight);
    document.getElementById("handleBox").value = "MAKE_RESERVATION";
    document.getElementById("requestBox").value = "";
}
function getReservationByID()
{
    window.scrollTo(0,document.body.scrollHeight);
    document.getElementById("handleBox").value = "GET_RESERVATION_BY_ID";
    document.getElementById("requestBox").value = "";
}
function getReservationByUser()
{
    window.scrollTo(0,document.body.scrollHeight);
    document.getElementById("handleBox").value = "GET_RESERVATION_BY_USER";
    document.getElementById("requestBox").value = "";
}
function cancelReservation()
{
    window.scrollTo(0,document.body.scrollHeight);
    document.getElementById("handleBox").value = "CANCEL_RESERVATION";
    document.getElementById("requestBox").value = "";
}