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
</div>
<p>Choose X or O: </p>
<input type="radio" name="player" value="O">O</>
<input type="radio" name="player" value="X">X</>

<script>

var ticktacktoe = new Vue({
    el: '#ticktacktoe',
    data: {
        gameBoard: []
    }
});


function sendPlay(value){

    var play = {place: value, player: player};

    var game = { play: play, 'gameBoard': ticktacktoe.gameBoard};

    var data = JSON.stringify(game);
    data = "game:" + data;

    $.ajax({
        contentType: 'application/json',
        data: {
            o: data
        },
        dataType: 'json',
        success: function(data){
            resetBoard(data)
        },
        error: function(data){
            console.log("failed" + data);
        },
        processData: false,
        type: 'POST',
        url: '/ticktacktoe/play'
        });
    }


function resetBoard(jsonData) {
    ticktacktoe.gameBoard = jsonData.game.gameBoard
}

$("input:radio[name=player]").click(function() {
     player = $(this).val();
    $("input:radio[player]").disabled = true;
});


</script>