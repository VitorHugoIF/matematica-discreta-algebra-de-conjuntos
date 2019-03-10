/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function (){

    $(document).on('click', '#btnSolveExercises', function (e) {
       
        e.preventDefault();
    
        var exercise = $("#Selectexercises").val();
        
        if (exercise == 1) {
            
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
                    table+= '<tr><td> Jogos </td><td>' + string + '</td></tr>';
                    $("#bodyTableExercises").html(table);
                    $("#tableExercises").fadeIn("slow");
                    
                    
                },
                error: function (data) {
                    console.log(data);
                }

            });
            /**/
        }else if (exercise == 2) {
            
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
            
        }else if (exercise == 3) {
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
        
    });
});

