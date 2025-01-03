$(document).ready(function() {

	$(".cancel-icon").click(function() {
	    var schedulingId = $(this).data("scheduling-id");
	    var personId = $(this).data("person-id");

	    $.post("cancelPerson", {
	        schedulingId: schedulingId,
	        personId: personId
	    }, function() {
	        $("a[data-person-id='" + personId + "']").closest("tr").remove();
			
			if ($(".form-table table tr").length <= 1)
				window.location.href = "cancel_page";
	    }).fail(function() {
	        alert("Erro ao cancelar a pessoa.");
	    });
	});


	$("#cancel-all").click(function() {
		
	    var schedulingId = $(this).data("scheduling-id");
		
		alert(schedulingId);

	    $.post("cancelAll", { schedulingId: schedulingId }, function() {
	        $(".form-table table").find("tr:gt(0)").remove();
			
			window.location.href = "cancel_page";
	    }).fail(function() {
	        alert("Erro ao cancelar o agendamento.");
	    });
	});
});
