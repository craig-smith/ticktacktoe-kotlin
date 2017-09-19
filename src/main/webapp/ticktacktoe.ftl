<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello Kotlin</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://unpkg.com/vue"></script>
    <style>
        td {height: 60px; width: 60px; padding: 0px; margin: 0px}
        tr {padding: 0px; margin: 0px}

    </style>
</head>

<h1>Tick Tack Toe</h1>
<p>The rules are simple, Pick a player and then pick a spot. Winner has three in a row on the board</p>

<div id="ticktacktoe">
    <table title="tick tack toe">
        <tr>
            <td style="border-right:solid; border-bottom:solid" onclick="sendPlay(1)">{{gameBoard[0]}}</td>
            <td style="border-bottom:solid; border-left:solid; border-right:solid" onclick="sendPlay(2)">
                {{gameBoard[1]}}
            </td>
            <td style="border-left:solid; border-bottom:solid" onclick="sendPlay(3)"></td>
        </tr>
        <tr>
            <td style="border-right:solid; border-bottom:solid; border-top:solid" onclick="sendPlay(4)"></td>
            <td style="border-bottom:solid; border-left:solid; border-right:solid; border-top:solid"
                onclick="sendPlay(5)"></td>
            <td style="border-left:solid; border-top:solid; border-bottom:solid" onclick="sendPlay(6)"></td>
        </tr>
        <tr>
            <td style="border-right:solid; border-top:solid" onclick="sendPlay(7)"></td>
            <td style="border-top:solid; border-left:solid; border-right:solid" onclick="sendPlay(8)"></td>
            <td style="border-left:solid; border-top:solid" onclick="sendPlay(9)"></td>
        </tr>
    </table>
    <p>Choose X or O: </p>
    <input type="radio" name="player" value="O"/>
    <input type="radio" name="player" value="X"/>
</div>

<script>
var ticktacktoe = new Vue({
    el: '#ticktacktoe',
    data: {
        gameBoard: null
    }
});


function sendPlay(value){

    var play;
    play.place = value;
    play.player = player;

    var game;
    game.play = play;
    game.gameBoard = ticktacktoe.gameBoard;

    $.ajax({
        contentType: 'application/json',
        data: {
            JSON.stringify(game);
        },
        dataType: 'json',
        success: function(data){
            resetBoard(data)
        },
        error: function(){
            app.log("failed");
        },
        processData: false,
        type: 'GET',
        url: '/ticktacktoe/play'
        });
    };
}

function resetBoard(jsonData) = function() {
    ticktacktoe.gameBoard = jsonData.game.gameBoard
}

$("input:radio[player]").click(function() {
    var player = $(this).val();
    $("input:radio[player]").disable()
});


</script>