/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function (){
    var globalJson = new Array();
    
    $.ajax({
        url: "readFile",
        type: "GET",
        data: "fileName=Exercicio1.txt",
        success: function (data) {
            globalJson[0] = data;
            var json = data;
            console.log(data);
            var arr = new Array();
            var string = "";
            
            if (json.listSets[0].length !=0) {
                for (var j = 0; j < json.listSets[0].elements.length; j++) {
                    arr.push(json.listSets[0].elements[j].value);
                }
                string = arr.join();
                string = json.listSets[0].name + " = { " + string + " }";
                $("#object1Exercise1").val(string);
            }
            
            if (json.listSets[1].length != 0) {
                var arr2 = new Array();
                var string2 = "";
                for (var j = 0; j < json.listSets[1].elements.length; j++) {
                    arr2.push(json.listSets[1].elements[j].value);
                }
                string2 = arr2.join();
                string2 = json.listSets[1].name + " = { " + string2 + " }";
                $("#object2Exercise1").val(string2);
            } 
            
        },
        error: function (data) {
            console.log(data);
        }

    });
    
    $.ajax({
        url: "readFile",
        type: "GET",
        data: "fileName=Exercicio2.txt",
        success: function (data) {
            globalJson[1] = data;
            var json = data;
            console.log(data);
            
            if (json.listSets[0].length != 0) {
                var arr = new Array();
                var string = "";
                for (var j = 0; j < json.listSets[0].elements.length; j++) {
                    arr.push(json.listSets[0].elements[j].value);
                }
                string = arr.join();
                string = json.listSets[0].name + " = { " + string + " }";
                $("#object1Exercise2").val(string);
            }
            if (json.listSets[1].length != 0) {
                var arr2 = new Array();
                var string2 = "";
                for (var j = 0; j < json.listSets[1].elements.length; j++) {
                    arr2.push(json.listSets[1].elements[j].value);
                }
                string2 = arr2.join();
                string2 = json.listSets[1].name + " = { " + string2 + " }";
                $("#object2Exercise2").val(string2);
            }
            if (json.listSets[2].length != 0) {
                var arr3 = new Array();
                var string3 = "";
                for (var j = 0; j < json.listSets[2].elements.length; j++) {
                    arr3.push(json.listSets[2].elements[j].value);
                }
                string3 = arr3.join();
                string3 = json.listSets[2].name + " = { " + string3 + " }";
                $("#object3Exercise2").val(string3);
            }  
        },
        error: function (data) {
            console.log(data);
        }

    });
    
    $.ajax({
        url: "readFile",
        type: "GET",
        data: "fileName=Exercicio3.txt",
        success: function (data) {
            globalJson[2] = data;
            var json = data;
            console.log(data);
            
            if (json.listElements.length != 0) {
                var arr1 = new Array();
                arr1.push(json.listElements[0].value);
                var string1 = arr1.join();

                string1 = json.listElements[0].name + " = " + string1 + "";
                $("#object1Exercise3").val(string1);
            }
            if (json.listSets[0].length != 0) {
                var arr = new Array();
                var string = "";
                for (var j = 0; j < json.listSets[0].elements.length; j++) {
                    arr.push(json.listSets[0].elements[j].value);
                }
                string = arr.join();
                string = json.listSets[0].name + " = { " + string + " }";
                $("#object2Exercise3").val(string);
            }
            if (json.listSets[1].length != 0) {
                var arr2 = new Array();
                var string2 = "";
                for (var j = 0; j < json.listSets[1].elements.length; j++) {
                    arr2.push(json.listSets[1].elements[j].value);
                }
                string2 = arr2.join();
                string2 = json.listSets[1].name + " = { " + string2 + " }";
                $("#object3Exercise3").val(string2);
            }
            if (json.listSets[2].length != 0) {
                var arr3 = new Array();
                var string3 = "";
                for (var j = 0; j < json.listSets[2].elements.length; j++) {
                    arr3.push(json.listSets[2].elements[j].value);
                }
                string3 = arr3.join();
                string3 = json.listSets[2].name + " = { " + string3 + " }";
                $("#object4Exercise3").val(string3);
            }

        },
        error: function (data) {
            console.log(data);
        }

    });
    
    $(document).on('click', '#btnSolveExercises', function (e) {
       
        e.preventDefault();
    
        var exercise = $("#Selectexercises").val();
        
        if (exercise == 1) {
            if (globalJson[0].listSets.length < 2) {
                alert('Conjunto problema invalido! Precisa-se de dois conjuntos!');
            }else{
                $.ajax({
                    url: "operations",
                    type: 'GET',
                    data: "radioOperation=solveExerciseOne&fileName=Exercicio1.txt",
                    success: function (data) {
                        $("#bodyTableExercises").html("");
                        var json = JSON.parse(data);
                        console.log(JSON.parse(data));

                        var arr = new Array();
                        var string = "";
                        for (var i = 0; i < json[0].pairs.length; i++) {
                            arr.push("( Time: " + json[0].pairs[i].pair[0].value + " x  Time: " + json[0].pairs[i].pair[1].value + ") ");
                        }
                        string = arr.join("; ");
                        var table;
                        table += '<tr><td> Jogos </td><td>' + string + '</td></tr>';
                        $("#bodyTableExercises").html(table);
                        $("#tableExercises").fadeIn("slow");


                    },
                    error: function (data) {
                        console.log(data);
                    }

                });
            }
            
        }else if (exercise == 2) {
            if (globalJson[1].listSets.length < 3) {
                alert('Conjunto problema invalido! Precisa-se de tres conjuntos!');
            }else{
                $.ajax({
                    url: "operations",
                    type: 'GET',
                    data: "radioOperation=solveExerciseTwo&fileName=Exercicio2.txt",
                    success: function (data) {
                        $("#bodyTableExercises").html("");
                        var json = JSON.parse(data);
                        console.log(json);

                        var arr = new Array();
                        var string = "";
                        var arr2 = new Array();
                        var string2 = "";
                        var table = "";

                        for (var i = 0; i < json[0].elements.length; i++) {
                            if (json[0].elements[i].value !== "vazio") {
                                arr.push(json[0].elements[i].value);
                            }

                        }
                        string = arr.join(", ");
                        table += '<tr><td> Aprovados </td><td>' + string + '</td></tr>';

                        for (var i = 0; i < json[1].elements.length; i++) {
                            if (json[1].elements[i].value !== "vazio") {
                                arr2.push(json[1].elements[i].value);
                            }

                        }
                        string2 = arr2.join(", ");
                        table += '<tr><td> Media em pelo menos uma disciplina </td><td>' + string2 + '</td></tr>';
                        $("#bodyTableExercises").html(table);
                        $("#tableExercises").fadeIn("slow");
                    },
                    error: function (data) {
                        console.log(data);
                    }

                });
            }
            
        }else if (exercise == 3) {
            if (globalJson[2].listSets.length < 3 || globalJson[2].listElements.length < 1) {
                alert('Conjunto problema invalido! Precisa-se de tres conjuntos e um elemento!');
            }else{
                $.ajax({
                url: "operations",
                type: 'GET',
                data: "radioOperation=solveExerciseThree&fileName=Exercicio3.txt",
                success: function (data) {
                    $("#bodyTableExercises").html("");
                    var json = JSON.parse(data);
                    console.log(json);
                    
                    var res = json[0] ? "Recebera o premio" : "Nao recebera o premio";
                    var table='<tr><td> Resposta </td> <td> '+res+' </td></tr>';
                    $("#bodyTableExercises").html(table);
                    $("#tableExercises").fadeIn("slow");
                    
                },
                error: function (data) {
                    console.log(data);
                }

            });
            }
            
        }
        
    });
});

