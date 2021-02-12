$(document).ready(function(){
	
	$("form").find("input[type=checkbox]").change(function(){
		var span=$("span").eq(0)
		$("tr.dorucak").slideToggle()
			if(span.text() == "Prikazi Dorucak"){
				span.text("Sakrij Dorucak")
			} else {
				span.text( "Prikazi Dorucak")
			}
	})
	
	$("form").submit(function(){
		var forma = $(this)
		var soba = $("#soba").find(":selected").text()
		var ulazak = forma.find("input[name=ulazak]").val()
		var izlazak = forma.find("input[name=izlazak]").val()
		var gost = forma.find("input[name=gost]").val()
		var placanje = forma.find("input[name=placanje]:checked").val()
		var dorucak = $("input:checkbox[name=dorucak]:checked").val()
		
		console.log(soba, ulazak, izlazak, gost, placanje, dorucak)
		
		if(soba == "" || ulazak == "" || izlazak == "" || gost == "" ||
		 placanje == "" || placanje == null || dorucak == "" || dorucak == null){
			alert("Nisu uneta sva polja")
			return false
		}
		
		if(placanje == "gotovina" && (soba == "apartman" || soba == "studio")){
			alert("Studio i apartman se mogu platiti samo karticom")
			return false
		}
		
		if(soba == "dvokrevetna" && dorucak == "omlet"){
			alert("U dvokrevetnoj sobi se ne sme jesti omlet :) !")
			return false
		}
		
		if(!(gost.match("^[a-zA-Z]+$") != null)){
			alert("Korisnicko ime ne sme da zadrzi brojeve i znakove")
			return false
		}
		
		return true
	})
})