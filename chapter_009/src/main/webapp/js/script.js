function validate() {
    var result = true;
    $(".error").each(function(index) {
        $(this).removeClass("error");
    });

    $(".main-fields input").each(function(index) {
        if ($(this).val() == "") {
            $(this).addClass("error");
            result = false;
        }
    });
    if (!result) {
        event.preventDefault();
    }
    return result;
}

function createCity(curPath) {
    var valNewCity = $(".city input").val();
    var valNewCountry = $(".country input").val();
    if (valNewCity != "" && valNewCountry !="" ) {
        $(".city input").removeClass("error");
        $(".country input").removeClass("error");
        $.ajax({
            url: curPath + "/add_city",
            dataType : "json",
            data : {country : valNewCountry, city : valNewCity},
            type : "post",
            success: function (data) {
                var resStr = "<div>" +
                    "<div class='city city-block'>"+ valNewCity +"</div>" +
                    "<div class='country city-block'>"+ valNewCountry +"</div>" +
                    "<div class='check'><input type='radio' checked value='" + data.id + "' name='active_city'/></div></div>";
                $(".cities").append(resStr);
            }
        });
    } else {
        if (valNewCity == "") {
            $(".city input").addClass("error");
        }
        if (valNewCountry == "") {
            $(".country input").addClass("error")
        }
    }
    event.preventDefault();
}