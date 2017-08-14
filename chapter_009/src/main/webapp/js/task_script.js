
function changeFilter(curPath) {
    var idFil = $(".filter_en").val();
    $.ajax({
        url: curPath + "/task/list",
        dataType : "json",
        data : {filter_id : idFil},
        type : "post",
        success: function (data) {
            $(".filter_val option").each(function( index ) {
                $(this).remove();
            });
            $(data).each(function( index ) {
                $(".filter_val").append("<option value=" + this.id + ">" + this.name + "</option>");
            });
        }
    });
}

function setFilter(curPath) {
    window.location.replace(curPath + "/task/list?filterId=" + $(".filter_en").val() + "&filter_val=" + $(".filter_val").val());
}