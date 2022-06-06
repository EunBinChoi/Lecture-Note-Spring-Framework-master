let agree = false;
$("#agree").click(() => { agree = !agree }); 

$("#delete").submit(() => { 
	if(!agree) {
		alert("Please check agree box, if you really want to cancel your registership.");
	}
	return agree;
}); 

