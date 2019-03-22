/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $.ajax({
        url: "operation.jsp",
        success: function (data) {
            $("#content").html(data);
        },
        error: function (data) {
            console.log(data);
        }

    });

    $(document).on("click", "#menuOperations", function (e) {
        e.preventDefault();
        $.ajax({
            url: "operation.jsp",
            success: function (data) {
                $("#content").html(data);
            },
            error: function (data) {
                console.log(data);
            }
        });
    });
    
    $(document).on("click", "#menuExercises", function (e) {
        e.preventDefault();
        $.ajax({
            url: "exercises.jsp",
            success: function (data) {
                $("#content").html(data);
            },
            error: function (data) {
                console.log(data);
            }
        });
    });
    $(document).on("click", "#menuRelations", function (e) {
        e.preventDefault();
        $.ajax({
            url: "relation.jsp",
            success: function (data) {
                $("#content").html(data);
            },
            error: function (data) {
                console.log(data);
            }
        });
    });

});
