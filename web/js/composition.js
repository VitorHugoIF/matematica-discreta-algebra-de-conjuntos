/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    
    $('#divComposition').hide();
    $('#divSets').hide();
    $('#divPairs').hide();
    $('#divSubmit').hide();
    $('#tableComposition').hide();
    
    var globalFileName;
    var globalSave;
    $(document).on("change", "#selectArchiveComposition", function (e) {
        var data = $(this)[0].files[0].name;
        globalFileName = data;
        $.ajax({
            url: "readFile",
            type: "GET",
            data: "fileName=" + data,
            success: function (data) {
                globalSave = data;
                $('#divComposition').fadeIn("slow");
                $("#formSelectArchiveComposition").fadeOut("fast");
                console.log(data);
            },
            error: function (data) {
                console.log(data);
            }

        });
    });
    
    $(document).on("change", "input[name=radioCompositionRelations]", function (e) {
        
        var content1 = "";
        var content2 = "";
        var content3 = "";
        
        if ($(this).prop('id') === 'arbitraryCompositionRelationship') {
            $('#divPairs').fadeIn('slow');
            $('#divSubmit').fadeIn("slow");
        } else {
            $('#divPairs').fadeOut('fast');
            if (globalSave.listSets.length > 2) {

                var arr = new Array();
                var string = "";
                var arr2 = new Array();
                var string2 = "";
                var arr3 = new Array();
                var string3 = "";

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
                
                for (var i = 0; i < globalSave.listSets[2].elements.length; i++) {
                    arr3.push(globalSave.listSets[2].elements[i].value);
                }
                string3 = arr3.join();
                content3 += "<option value=" + globalSave.listSets[2].name + ">" + globalSave.listSets[2].name + " = { " + string3 + " }</option>";


                $("#set1").html(content1);
                $("#set2").html(content2);
                $("#set3").html(content3);
                $('#divSets').fadeIn('slow');
                $('#divSubmit').fadeIn("slow");

            } else {
                alert("Impossivel realizar a relação selecionada! Operandos Incompletos.");
                $('#divSubmit').fadeOut("fast");
            }
        }
    });
    
    $(document).on("submit", "#formComposition", function (e) {

        e.preventDefault();

        $.ajax({
            url: "composition",
            type: "GET",
            data: $(this).serialize() + "&fileName=" + globalFileName,
            success: function (data) {
                console.log(JSON.parse(data));
                var json = JSON.parse(data);
                
                if (json.length > 5) {
                    var result = "<tr class='alert alert-danger'><td  colspan='4'> PARES INCORRETOS </td> </tr>";
                    $('#bodyTableComposition').html(result);
                    $('#tableComposition').fadeIn('slow');
                }else{
                    $('#bodyTableComposition').html("");
                    var arr = new Array();
                    var string = "";
                    var arr1 = new Array();
                    var string1 = "";
                    var arr2 = new Array();
                    var string2 = "";
                    var arr3 = new Array();
                    var string3 = "";
                    
                    for (var i = 0; i < json[0].pairs.length; i++) {
                        if (json[0].pairs[i]) {
                            var pair = "( "+json[0].pairs[i].pair[0].value+", "+json[0].pairs[i].pair[1].value+" )";
                            arr.push(pair);
                        }
                    }
                    
                    string = arr.join("; ");
                    string = '<td>' + string + '</td>';
                    
                    for (var i = 0; i < json[1].elements.length; i++) {
                        if (json[1].elements[i]) {
                            arr1.push(json[1].elements[i].value);
                        }
                    }
                    
                    string1 = arr1.join(", ");
                    string1 = '<td>{ ' +string1 + ' }</td>';
                    
                    for (var i = 0; i < json[2].elements.length; i++) {
                        if (json[2].elements[i]) {
                            arr2.push(json[2].elements[i].value);
                        }
                    }
                    
                    string2 = arr2.join(", ");
                    string2 += '<td>{ ' + string2 + ' }</td>';
                    
                    string3 = json[3].join(", ");
                    string3 = '<td>' + string3 + '</td>';
                    
                    var result = '<tr>'+string+string1+string2+string3+'</tr>';
                    $('#bodyTableComposition').html(result);
                    $('#tableComposition').fadeIn('slow');
                }
            },
            error: function (data) {
                console.log(data);
            }
        });
    });
    
    $(document).on("keyup", "#pairsR", function (e) {
        $(this).val(Mask($(this).val()));
    });
    $(document).on("keyup", "#pairsS", function (e) {
        $(this).val(Mask($(this).val()));
    });
    
    function Mask(text) { 
        return text.replace(/[^0-9,;]/gi,"");  
    } 

});


