function createItem(curPath) {
    var descr = $(".descr").val();
    var done = $(".done").val();
    $.ajax({
        url: curPath + "/",
        dataType : "json",
        data : {descr : descr,
                done: done},
        type : "post",
        success: function (data) {
            $(".data-items").append('<div class="row">' +
                                        '<div class="inline">'+ data.descr +'</div>' +
                                        '<div class="inline">'+ data.created +'</div>' +
                                        '<div class="inline">'+ data.done +'</div>' +
                                    '</div>'
                                    );
            $(".descr").val("");
            $(".done").val("");
        }
    });
    event.preventDefault();
}

function setFilter(curPath) {
    var valFilter = $(".select-filter").val();
    $.ajax({
        url: curPath + "/",
        dataType : "json",
        data : {filterDone : valFilter},
        type : "get",
        success: function (data) {
            $(".data-items").empty();
            for (i = 0; i < data.length; i++) {
                $(".data-items").append('<div class="row">' +
                    '<div class="inline">'+ data[i].descr +'</div>' +
                    '<div class="inline">'+ data[i].created +'</div>' +
                    '<div class="inline">'+ data[i].done +'</div>' +
                    '</div>'
                );
            }
        }
    });
}