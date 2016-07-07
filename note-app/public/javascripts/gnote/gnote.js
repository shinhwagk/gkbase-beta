//$('#exampleModal').on('show.bs.modal', function (event) {
//  var button = $(event.relatedTarget) // Button that triggered the modal
//  var recipient = button.data('whatever') // Extract info from data-* attributes
//  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
//  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
//  var modal = $(this)
//  modal.find('.modal-title').text('New message to ' + recipient)
//  modal.find('.modal-body input').val(recipient)
//})


$(function(){$('#dir-add-button-open').on('shown.bs.modal', function (event) {
//  var button = $(event.relatedTarget)
//  var recipient = button.data('whatever')
//  var modal = $(this)
//  modal.find(".modal-body input[name='dir-add-name-val']").val('bbb')
//  modal.find('.modal-title').text('New message to ' + recipient)
//event.preventDefault();
// alert($('#ccc').serialize())

})})

$(function(){$('#dir-update-modal').on('shown.bs.modal', function (event) {
  $("#dir-update-name").focus()
})})

$(function(){
    $('#dir-update-name').bind('keypress',function(event){
    alert(111)
        if(event.keyCode == "13"){

            dir_update_exec()
        }
    });
});

//远端模态框---重置
$(function(){$('#content-update-modal').on("hidden.bs.modal", function (e) {
      $(e.target).removeData("bs.modal").find(".modal-content").empty();
  });
})




function content_update_exec(){
//var button = $(event.relatedTarget) // Button that triggered the modal
//  var recipient = button.data('whatever')
//  alert(recipient)
    var inputs = $("#content-update-modal").find(".modal-body").find("input")
    var d_id = inputs[1].value
//    var content_2 = textareas[1].value
//alert()
        $.ajax({
            type: "POST",
    		url: "/app/note/content/put",
    		data: $('form#content-update-form').serialize(),
            success: function(msg){
                $("#content-update-modal").modal('hide');
                location.href = "/app/note/id/" + d_id
            },
    		error: function(error) {
                alert(error.status);
                alert(error.readyState);
                alert(error.textStatus);
            },
        });
}

function dir_update_exec(){
    var inputs = $("#dir-update-modal").find(".modal-body").find("input")
    var d_id = inputs[0].value
        $.ajax({
            type: "POST",
    		url: "/app/note/dir/put",
    		data: $('form#dir-update-form').serialize(),
            success: function(msg){
                $("#dir-update-modal").modal('hide');
                location.href = "/app/note/id/" + d_id
            },
    		error: function(error) {
                alert(error.status);
                alert(error.readyState);
                alert(error.textStatus);
            },
        });
}


$(function(){
    $("#dir-add-button-success").click(function(){
//        alert(jsRoutes.controllers.gNote.dirAdd().url)
//        $.post(
//        jsRoutes.controllers.gNote.dirAdd().url,
//        function(data) {
//              alert(data);
//    })
        $.ajax({
            type: "POST",
    		url: jsRoutes.controllers.gNote.dirAdd().url,
    		data: $('form#dir-add-form').serialize(),
            success: function(msg){
//                alert($('form#dir-add-form').serialize())
                $("#dir-add-modal").modal('hide');
//                alert($('form#dir-add-form'))
                location.href = jsRoutes.controllers.gNote.c(1).url
            },

    		error: function(error) {
                alert(error.status);
                alert(error.readyState);
                alert(error.textStatus);
            },
        });
    });
});


$(document).ready(function(){
  $("button#content-add").click(function(){
   alert(11)

  });
});

//content delete 废弃
//$(document).ready(function(){
//  $("button#content_delete").click(function(){
//   var dir_id =$("input[name=dir_id]").val()
//   var content_id = $("input[name='content_radio'][type='radio']:checked").val()
//    $.ajax({
//        type: "DELETE",
//       	url: "/app/note/content/"+ content_id,
//       	success: function(msg){
//            location.href = "/app/note/id/" + dir_id
//        },
//        error: function(error) {
//            alert(error.status);
//            alert(error.readyState);
//            alert(error.textStatus);
//        },
//    });
//  });
//});
//废弃删除
//$(document).ready(function(){
//  $("button#dir_delete").click(function(){
//   var dir_id =$("input[name=dir_id]").val()
//    $.ajax({
//        type: "DELETE",
//       	url: "/app/note/dir/"+ dir_id,
//       	success: function(msg){
//            location.href = "/app/note/id/" + dir_id
//        },
//        error: function(error) {
//            alert(error.status);
//            alert(error.readyState);
//            alert(error.textStatus);
//        },
//    });
//  });
//});

/*
$(function(){$('#dir-add-button').on('show.bs.modal', function (event) {
var button = $(event.relatedTarget)
  var recipient = button.data('whatever')
  var modal = $(this)
  modal.find('.modal-title').text('New message to ' + recipient)
  modal.find(".modal-body textarea[name='abc']").val('bbb')


//  event.preventDefault();
alert($('#ccc').serialize())
})})

*/

$(document).ready(function(){
    $("button#content-update-button-success").click(function(){
        alert('adf')
    })
})

function category_delete_exec(id){
alert(111)
//alert(jsRoutes.controllers.gNote.delete_directory(id).url)
    $.ajax({
        type: "GET",
        url: "/app/note/dir/delete/" + id,
        success: function(msg){
            alert(11)
        },
        error: function(error) {
            alert(error.status);
            alert(error.readyState);
            alert(error.textStatus);
        },
    });
}