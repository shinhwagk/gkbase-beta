//$('#exampleModal').on('show.bs.modal', function (event) {
//  var button = $(event.relatedTarget) // Button that triggered the modal
//  var recipient = button.data('whatever') // Extract info from data-* attributes
//  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
//  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
//  var modal = $(this)
//  modal.find('.modal-title').text('New message to ' + recipient)
//  modal.find('.modal-body input').val(recipient)
//})


$(function(){$('#dir-add-button').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget)
  var recipient = button.data('whatever')
  var modal = $(this)
//  modal.find('.modal-title').text('New message to ' + recipient)
  modal.find(".modal-body input[name='dir-name']").val('bbb')

  //event.preventDefault();
 // alert($('#ccc').serialize())
})})


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
$(function(){
    $("#ooo").click(function(){
    alert($('form#contact').serialize())
    })
})
