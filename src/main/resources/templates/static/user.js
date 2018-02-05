$('#userLoginBtn').click(function () {
    $.ajax({
        type: "POST",
        url: "/user/v1/login",
        data: $('#loginModal form').serialize(),// 你的formid
        error: function (request) {
            alert("Connection error");
        },
        success: function (data, status, xhr) {
            var code = data.code;
            switch (code) {
                case (200):

//                            alert("欢迎回来");
                    sessionStorage.setItem("xrf_", xhr.getResponseHeader("AUTH"));
                    sessionStorage.setItem("username", xhr.getResponseHeader("username"));
                    $('#loginModal').modal('hide');
                    setStatus();
                    break;
                default:
                    alert(data.message);
            }

        }
    });
    return false;
});

$('#userRegisterBtn').click(function () {
    $.ajax({
        type: "PUT",
        url: "/user/v1/login",
        data: $('#registerModal form').serialize(),// 你的formid
        error: function (request) {
            alert("Connection error");
        },
        success: function (data, status, xhr) {
            var code = data.code;
            switch (code) {
                case (200):
                    alert("注册成功");
                    sessionStorage.setItem("xrf_", xhr.getResponseHeader("AUTH"));
                    sessionStorage.setItem("username", xhr.getResponseHeader("username"));
                    $('#registerModal').modal('hide');
                    setStatus();
                    break;
                default:
                    alert(data.message);
            }
        }
    });
    return false;
});

$('.Logout').click(function () {
    $.ajax({
        type: "DELETE",
        headers: {
            AUTH: sessionStorage.getItem("xrf_")
        },
        url: "/user/v1/login",
        data: null,// 你的formid
        error: function (request) {
            alert("请先登录");
        },
        success: function (data) {
            var code = data.code;
            switch (code) {
                case (200):
                    // alert("OK");
                    sessionStorage.removeItem("username");
                    sessionStorage.removeItem("AUTH");
                    setStatus();
                    break;
                default:
                    alert(data.message);
            }
        }
    });
    return false;
});

function setStatus() {
    if (sessionStorage.getItem("username") == null) {
        $(".unLogin").show();
        $(".Logout").hide();
        $("#username").hide();
        $("#shopcarSize").text(0);
    } else {
        $(".unLogin").hide();
        $(".Logout").show();
        $("#username").show();
        $("#username a").text("欢迎您，" + sessionStorage.getItem("username"));
        getShopcarSize();
    }
}

$("#bookCarbtn").click(function () {
    if (sessionStorage.getItem("username") == null) {
        alert("请先登录");
        return false;
    } else {
        location.href = "/book/shopcar";
    }
});

function getShopcarSize() {
    $.ajax({
        type: "GET",
        headers: {
            AUTH: sessionStorage.getItem("xrf_")
        },
        url: "/book/v1/shopcar/size",
        data: null,
        error: function (request) {
            alert("请先登录");
        },
        success: function (data) {
            var code = data.code;
            switch (code) {
                case (200):
                    $("#shopcarSize").text(data.data.size);
                    break;
                default:
                    console.log(data.message);
            }
        }
    });
}