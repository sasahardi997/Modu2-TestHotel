var baseURL = ""

function popuniBaseURL() {
	$.get("baseURL", function(odgovor) {
		console.log(odgovor)

		if (odgovor.status == "ok") {
			baseURL = odgovor.baseURL 
			$("base").attr("href", baseURL) 
		}
	})
	console.log("GET: " + "baseURL")
}

function cenaBoravka(brojKreveta, cenaNocenja, terasa){
	ukupnaCena = ''
	ukupnaCena = (brojKreveta * cenaNocenja)
	if(terasa){
		ukupnaCena *= 1.08
	}
	return ukupnaCena
}

function popuniSobe(){
	$.get("Sobe", function(odgovor){
		console.log(odgovor)
		
		if(odgovor.status == "ok"){
			var sobe = odgovor.sobe
			var option = ''
			$("#select").find('option').remove()
			
			var tabela = $("table.tabela")
			tabela.find("tr:gt(0)").remove()
			for(var it in sobe){
				cenaN = cenaBoravka(sobe[it].brojKreveta, sobe[it].cenaNocenja, sobe[it].terasa)
				tabela.append(
					'<tr>' +
						'<th>'+ sobe[it].tip +'</th>' +
						'<th>'+ sobe[it].brojKreveta +'</th>' +
						'<th>'+ sobe[it].cenaNocenja +'</th>' +
						'<th>'+ sobe[it].terasa +'</th>' +
						'<th>'+ cenaN.toFixed(2) +'</th>' +
					'</tr>'
				)
				option += '<option value="' + (parseInt(it) + 1) + '">' + sobe[it].tip + '</option>';
			}
			$("#select").append(option)
			console.log(option)
		}
	})
	console.log("GET: Sobe")
}

function popuniPodatkeOSobama(){
	var tipSobe = $("select[name=id]").val()
	
	var params = {
		tipSobe : tipSobe
	}
	
	$.get("Sobe/Details", params, function(odgovor){
		var soba = odgovor.soba
		  var tabela1 = $("table.tabela1")
        if (odgovor.status != "ok"){
            window.location.replace("zadatak7.html")
        }
		$("input[name=sobaID]").val(soba.id)
		tabela1.find("input[name=brojKreveta]").val(soba.brojKreveta)
		tabela1.find("input[name=cenaNocenja]").val(soba.cenaNocenja)
		
		if (soba.terasa == true){
	        tabela1.find("input[name=terasa]:last-child").attr('checked', false)
	        tabela1.find("input[name=terasa]:first-child").attr('checked', true)      
        }
        if (soba.terasa == false){
            tabela1.find("input[name=terasa]:first-child").attr('checked', false)
            tabela1.find("input[name=terasa]:last-child").attr('checked', true)
        }
	})
}

function izmeniSobu(){
	var id = $("#select").find(":selected").val()
	var cenaNocenja = $("input[name=cenaNocenja]").val()
	var brojKreveta = $("input[name=brojKreveta]").val()
	var terasa = $("input[name=terasa]:checked").val()
	
	var params = {
		id: parseInt(id),
		cenaNocenja : cenaNocenja,
		brojKreveta : brojKreveta,
		terasa : terasa
	}
	console.log(params)
	
	$.post("Sobe/Edit", params, function(odgovor){
		if(odgovor.status == "greska"){
			console.log("greska")
		}
		if(odgovor.status == "ok"){
			popuniSobe()
		}
	})
}

$(document).ready(function(){
	popuniBaseURL()
	popuniSobe()
	
	$("select[name=id]").change(function(){
		popuniPodatkeOSobama()
	})
	
	$("form").eq(1).submit(function(){
		izmeniSobu()
		return false
	})
})