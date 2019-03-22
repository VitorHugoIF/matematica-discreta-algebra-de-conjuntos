/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    $('#divRelations').hide();
    $('#divSets').hide();
    $('#divPairs').hide();
    $('#divSubmit').hide();
    $('#tableRelations').hide();
    var globalFileName;
    var globalSave;
    $(document).on("change", "#selectArchiveRelations", function (e) {
        var data = $(this)[0].files[0].name;
        globalFileName = data;
        $.ajax({
            url: "readFile",
            type: "GET",
            data: "fileName=" + data,
            success: function (data) {
                globalSave = data;
                $('#divRelations').fadeIn("slow");
                $("#formSelectArchiveRelations").fadeOut("fast");
                console.log(data);
            },
            error: function (data) {
                console.log(data);
            }

        });
    });

    $(document).on("change", "input[name=radioRelations]", function (e) {

        var content1 = "";
        var content2 = "";

        $('#divPairs').fadeIn('slow');


        if (globalSave.listSets.length > 1) {

            var arr = new Array();
            var string = "";
            var arr2 = new Array();
            var string2 = "";

            for (var j = 0; j < globalSave.listSets[0].elements.length; j++) {
                arr.push(globalSave.listSets[0].elements[j].value);
            }
            string = arr.join();
            content1 += "<option value=" + globalSave.listSets[0].name + ">" + globalSave.listSets[0].name + " = { " + string + " }</option>";

            for (var i = 0; i < globalSave.listSets[1].elements.length; i++) {
                arr2.push(globalSave.listSets[1].elements[i].value);
            }
            string2 = arr2.join();
            content2 += "<option value=" + globalSave.listSets[1].name + ">" + globalSave.listSets[1].name + " = { " + string2 + " }</option>";


            $("#set1").html(content1);
            $("#set2").html(content2);
            $('#divSets').fadeIn('slow');
            $('#divSubmit').fadeIn("slow");

        } else {
            alert("Impossivel realizar a relação selecionada! Operandos Incompletos.");
            $('#divSubmit').fadeOut("fast");
        }
    });

    $(document).on("submit", "#formRelations", function (e) {

        e.preventDefault();

        $.ajax({
            url: "relations",
            type: "GET",
            data: $(this).serialize() + "&fileName=" + globalFileName,
            success: function (data) {
                console.log(data);
            },
            error: function (data) {
                console.log(data);
            }
        });
    });
    
    $(document).on("keyup", "#pairs", function (e) {
        $(this).val(Mask($(this).val()));
    });
    
    function Mask(text) { //variavel do parametro recebe o caractere digitado//  
        return text.replace(/[^0-9,;()]/gi,"");  
    } 
});

