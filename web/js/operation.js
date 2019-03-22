/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $("#divOperations").hide();
    $("#divSetsElements").hide();
    $("#tableOperations").hide();
    $("#btnReverse").hide();

    var globalSave = null;
    var globalFileName;
    var globalReverse;

    $(document).on("change", "#selectArchive", function (e) {
        var data = $(this)[0].files[0].name;
        globalFileName = data;
        $.ajax({
            url: "readFile",
            type: "GET",
            data: "fileName=" + data,
            success: function (data) {
                globalSave = data;
                $("#divOperations").fadeIn("slow");
                $("#formSelectArchive").fadeOut("fast");
                console.log(data);
            },
            error: function (data) {
                console.log(data);
            }

        });
    });

    $(document).on("change", "input[name=radioOperation]", function (e) {
        var operation = $(this).prop("id");
        var content1 = "";
        var content2 = "";

        if (operation === "pertinence") {
            $("#btnReverse").hide();
            if (globalSave.listElements.length > 0 && globalSave.listSets.length > 0) {

                for (var i = 0; i < globalSave.listElements.length; i++) {
                    //tansforma em array e depois em string, para tratar elemento nulo
                    var arr = new Array();
                    arr.push(globalSave.listElements[i].value);
                    var string = arr.join();

                    content1 += "<option value=" + globalSave.listElements[i].name + ">" + globalSave.listElements[i].name + " =  " + string + " </option>";
                }

                for (var i = 0; i < globalSave.listSets.length; i++) {
                    var arr = new Array();
                    var string = "";

                    for (var j = 0; j < globalSave.listSets[i].elements.length; j++) {
                        arr.push(globalSave.listSets[i].elements[j].value);
                    }
                    string = arr.join();
                    content2 += "<option value=" + globalSave.listSets[i].name + ">" + globalSave.listSets[i].name + " = { " + string + " }</option>";
                }

                $("#objects1").html(content1);
                $("#objects2").html(content2);
                $("#divSetsElements").fadeIn("slow");
                $("#objects2").show();
                $("#btnSubmitOperations").fadeIn("fast");
            } else {
                alert("Impossivel realizar a operacao selecionada, por favor selecione outra! Operandos Incompletos.");
                $("#btnSubmitOperations").fadeOut("fast");
            }


        } else if (operation === "contained" || operation === "containedProperly" || operation === "cartesianProduct") {
            $("#btnReverse").hide();

            if (globalSave.listSets.length > 0) {
                for (var i = 0; i < globalSave.listSets.length; i++) {
                    var arr = new Array();
                    var string = "";

                    for (var j = 0; j < globalSave.listSets[i].elements.length; j++) {
                        arr.push(globalSave.listSets[i].elements[j].value);
                    }
                    string = arr.join();
                    content1 += "<option value=" + globalSave.listSets[i].name + ">" + globalSave.listSets[i].name + " = { " + string + " }</option>";
                }
                $("#objects1").html(content1);
                $("#objects2").html(content1);
                $("#divSetsElements").fadeIn("slow");
                $("#objects2").show();
                $("#btnSubmitOperations").fadeIn("fast");
            } else {
                alert("Impossivel realizar a operacao selecionada, por favor selecione outra! Operandos Incompletos.");
                $("#btnSubmitOperations").fadeOut("fast");
            }

        } else if (operation === "union" || operation === "intersection") {
            $("#btnReverse").hide();
            if (globalSave.listSets.length > 0) {
                $("#objects1").html("<option>UNIÃO = todos os conjuntos</option>");
                $("#objects2").html("<option>UNIÃO = todos os conjuntos</option>");
                $("#divSetsElements").fadeIn("slow");
                $("#objects2").show();
                $("#btnSubmitOperations").fadeIn("fast");
            } else {
                alert("Impossivel realizar a operacao selecionada, por favor selecione outra! Operandos Incompletos.");
                $("#btnSubmitOperations").fadeOut("fast");
            }


        } else if (operation === "setOfParties") {
            $("#btnReverse").hide();
            console.log(globalSave);
            if (globalSave.listSets.length > 0) {
                for (var i = 0; i < globalSave.listSets.length; i++) {
                    var arr = new Array();
                    var string = "";

                    for (var j = 0; j < globalSave.listSets[i].elements.length; j++) {
                        arr.push(globalSave.listSets[i].elements[j].value);
                    }
                    string = arr.join();
                    content1 += "<option value=" + globalSave.listSets[i].name + ">" + globalSave.listSets[i].name + " = { " + string + " }</option>";
                }
                $("#objects1").html(content1);
                $("#objects2").html("");
                $("#divSetsElements").fadeIn("slow");
                $("#objects2").hide();
                $("#btnSubmitOperations").fadeIn("fast");
            } else {
                alert("Impossivel realizar a operacao selecionada, por favor selecione outra! Operandos Incompletos.");
                $("#btnSubmitOperations").fadeOut("fast");
            }

        }

    });

    $(document).on("submit", "#formOperations", function (e) {
        
        e.preventDefault();
        $.ajax({
            url: "operations",
            type: "GET",
            data: $(this).serialize() + "&fileName=" + globalFileName,
            success: function (data) {
                //console.log(data);
                var table = '';
                var json = JSON.parse(data);
                console.log("retorno");
                console.log(json);
                if (data === "true") {
                    $("#btnReverse").hide();
                    var result = "<div class='alert alert-success'> Verdadeiro </div>";
                    $("#tableOperations").hide();
                    $("#result").html(result);
                    $("#result").fadeIn("slow");
                } else if (data === "false") {
                    var result = "<div class='alert alert-danger'> Falso </div>";
                    $("#tableOperations").hide();
                    $("#result").html(result);
                    $("#result").fadeIn("slow");
                } else if (json.name === "UNIAO" || json.name === "INTERSECAO") {

                    if (json.elements.length == 0) {
                        $("#btnReverse").hide();
                        table += '<tr id="resultOperationsFunctions"><td>' + json.name + '</td><td> {vazio} </td></tr>';
                        $("#result").hide();
                        $("#bodyTableOperations").html(table);
                        $("#tableOperations").fadeIn("slow");
                    } else {
                        var arr = new Array();
                        var string = "";

                        for (var i = 0; i < json.elements.length; i++) {
                            if (json.elements[i].value !== "vazio") {
                                arr.push(json.elements[i].value);
                            }

                        }
                        string = arr.join(", ");
                        table += '<tr id="resultOperationsFunctions"><td>' + json.name + '</td><td>' + string + '</td></tr>';
                        $("#result").hide();
                        $("#bodyTableOperations").html(table);
                        $("#tableOperations").fadeIn("slow");
                    }

                } else if (json[0].name === "PRODUTO CARTESIANO") {

                    globalReverse = json;

                    $("#btnReverse").show();
                    var arr = new Array();
                    var string = "";
                    if (json[0].pairs[0].pair[0].value === "vazio") {
                        table += '<tr id="resultOperationsFunctions"><td>' + json[0].name + '</td><td> { vazio } </td></tr>';
                        $("#result").hide();
                        $("#bodyTableOperations").html(table);
                        $("#tableOperations").fadeIn("slow");
                    } else {
                        for (var i = 0; i < json[0].pairs.length; i++) {
                            arr.push("[ (conjunto: " + json[0].pairs[i].pair[0].set + ", valor: " + json[0].pairs[i].pair[0].value + "), (conjunto: " + json[0].pairs[i].pair[1].set + ", valor: " + json[0].pairs[i].pair[1].value + ") ]");
                        }
                        string = arr.join("; ");
                        table += '<tr id="resultOperationsFunctions"><td>' + json[0].name + '</td><td>' + string + '</td></tr>';
                        $("#result").hide();
                        $("#bodyTableOperations").html(table);
                        $("#tableOperations").fadeIn("slow");
                    }


                } else if (json[0][0].name === "CONJUNTO DAS PARTES") {
                    globalReverse = json;
                    $("#btnReverse").show();
                    console.log(json[0]);
                    var arr2 = new Array();
                    var string = "";

                    for (var i = 0; i < json[0].length; i++) {
                        var arr = new Array();
                        for (var j = 0; j < json[0][i].elements.length; j++) {
                            arr.push(json[0][i].elements[j].value);
                        }
                        arr2.push(arr.join(", "));
                    }
                    console.log(arr2);
                    string = arr2.join(" }, { ");
                    string = "{ " + string + " }";
                    table += '<tr id="resultOperationsFunctions"><td>' + json[0][0].name + '</td><td>' + string + '</td></tr>';
                    $("#result").hide();
                    $("#bodyTableOperations").html(table);
                    $("#tableOperations").fadeIn("slow");

                }

            },
            error: function (data) {
                console.log(data);
            }
        });
    });

    $(document).on("click", "#btnReverse", function (e) {
        e.preventDefault();
        console.log(globalReverse);
        if ($("#cartesianProduct").prop("checked") == true) {
            if (globalReverse[0].pairs[0].pair[0].value === "vazio") {
                string1 = '<tr> <td> Origem </td> <td> Operacao nao pode ser realizada! </td> </tr>';
            } else {
                var arr1 = new Array();
                var arr2 = new Array();

                for (var i = 0; i < globalReverse[1][0].elements.length; i++) {
                    arr1.push(globalReverse[1][0].elements[i].value);
                }
                for (var i = 0; i < globalReverse[1][1].elements.length; i++) {
                    arr2.push(globalReverse[1][1].elements[i].value);
                }

                var string1 = arr1.join();
                var string2 = arr2.join();

                string1 = '<tr> <td> Origem </td> <td> ' + globalReverse[1][0].name + ' = { ' + string1 + ' } ' + globalReverse[1][1].name + ' = { ' + string2 + ' } </td> </tr>';

            }
        }else if ($("#setOfParties").prop("checked") == true) {
            var arr1 = new Array();

            for (var i = 0; i < globalReverse[1].elements.length; i++) {
                arr1.push(globalReverse[1].elements[i].value);
            }
            
            var string1 = arr1.join();

            string1 = '<tr> <td> Origem </td> <td> ' + globalReverse[1].elements[0].set + ' = { ' + string1 + ' } </td> </tr>';

        }
     
        
        $("#bodyTableOperations").html($("#bodyTableOperations").html() + string1);
        $("#btnReverse").hide();

    });

});