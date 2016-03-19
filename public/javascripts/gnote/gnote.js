//$('#exampleModal').on('show.bs.modal', function (event) {
//  var button = $(event.relatedTarget) // Button that triggered the modal
//  var recipient = button.data('whatever') // Extract info from data-* attributes
//  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
//  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
//  var modal = $(this)
//  modal.find('.modal-title').text('New message to ' + recipient)
//  modal.find('.modal-body input').val(recipient)
//})


$(function(){$('#dir-add-button-open').on('show.bs.modal', function (event) {
//  var button = $(event.relatedTarget)
//  var recipient = button.data('whatever')
//  var modal = $(this)
//  modal.find(".modal-body input[name='dir-add-name-val']").val('bbb')
//  modal.find('.modal-title').text('New message to ' + recipient)
//event.preventDefault();
// alert($('#ccc').serialize())
})})
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
function abc(){
    alert($('input[name=inlineRadioOptions]:checked').val());
}


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
