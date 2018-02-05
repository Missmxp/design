var bookPrice;
$(function () {

//        $('#buyBtn').attr("disabled", "disabled");

    setStatus();
    getBook(getQueryString("bookid"));


    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

    function getBook(bookuid) {
        $.ajax({
            type: "GET",
            url: "/book/v1/detail/" + bookuid,
            data: null,
            error: function (request) {
                alert("Connection error");
            },
            success: function (result) {
                var book = result.data.book;
                $("#book_uid").val(book.uid);
                $("#book_name").text(book.name);
                $("#book_author").text(book.author);
                $("#book_price").text(book.price);
                $("#book_desc").text(book.description);
                $("#bookuid_buy").val(book.uid);
                $("#bookuid_add").val(book.uid);
                bookPrice = book.price;
            }
        });
    }

    $("#book_buy_num").change(function () {
        if ($("#book_buy_num").val() <= 0) {
            alert("请输入正确的数量");
            return;
        }
        if ($(this).val().length > 5) {
            alert("数量过多");
            return;
        }
        $("#bookPrice_buy").text("您需支付￥" + ($(this).val() * bookPrice));
        $('#buyBtn').removeAttr("disabled");
    })

    $('#buyBtn').click(function () {
        if ($("#book_buy_num").val() <= 0) {
            alert("请输入正确的数量");
            return;
        }
        if ($(this).val().length > 5) {
            alert("数量过多");
            return;
        }
        ;
        $.ajax({
            type: "POST",
            headers: {
                AUTH: sessionStorage.getItem("xrf_")
            },
            url: "/book/v1/buy",
            data: $('#buyModal form').serialize(),// 你的formid
            error: function (request) {
                alert("请您先去登录");
            },
            success: function (data) {
                if (data.code == 200) {
                    alert("购买成功");
                    $('#buyModal').modal('hide');
                } else
                    alert(data.message);
            }
        });
        return false;
    });

    $(function () {
        $('#addBtn').click(function () {
            if ($("#book_buy_num").val() <= 0) {
                alert("请输入正确的数量");
                return;
            }
            if ($(this).val().length > 5) {
                alert("数量过多");
                return;
            }
            ;
            $.ajax({
                type: "POST",
                headers: {
                    AUTH: sessionStorage.getItem("xrf_")
                },
                url: "/book/v1/shopcar",
                data: $('#shopCarModal form').serialize(),// 你的formid
                error: function (request) {
                    alert("请您先去登录");
                },
                success: function (data) {
                    if (data.code == 200) {
                        $('#shopCarModal').modal('hide');
                        location.reload(true);
                    } else
                        alert(data.message);

                }
            });
            return false;
        });
    });
});