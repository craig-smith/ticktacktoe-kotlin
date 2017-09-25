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
            <td style="border-right:solid; border-bottom:solid" onclick="sendPlay(1)">{{gameBoard[0].player}}</td>
            <td style="border-bottom:solid; border-left:solid; border-right:solid" onclick="sendPlay(2)">
                {{gameBoard[1].player}}
            </td>
            <td style="border-left:solid; border-bottom:solid" onclick="sendPlay(3)">{{gameBoard[2].player}}</td>
        </tr>
        <tr>
            <td style="border-right:solid; border-bottom:solid; border-top:solid" onclick="sendPlay(4)">{{gameBoard[3].player}}</td>
            <td style="border-bottom:solid; border-left:solid; border-right:solid; border-top:solid" onclick="sendPlay(5)">{{gameBoard[4].player}}</td>
            <td style="border-left:solid; border-top:solid; border-bottom:solid" onclick="sendPlay(6)">{{gameBoard[5].player}}</td>
        </tr>
        <tr>
            <td style="border-right:solid; border-top:solid" onclick="sendPlay(7)">{{gameBoard[6].player}}</td>
            <td style="border-top:solid; border-left:solid; border-right:solid" onclick="sendPlay(8)">{{gameBoard[7].player}}</td>
            <td style="border-left:solid; border-top:solid" onclick="sendPlay(9)">{{gameBoard[8].player}}</td>
        </tr>
    </table>
</div>
<p>Choose X or O: </p>
<input type="radio" name="player" value="O">O</input>
<input type="radio" name="player" value="X">X</input>

<script>

var ticktacktoe = new Vue({
    el: '#ticktacktoe',
    data: {
        gameBoard: [
                {place: 1, player: ''},
                {place: 2, player: ''},
                {place: 3, player: ''},
                {place: 4, player: ''},
                {place: 5, player: ''},
                {place: 6, player: ''},
                {place: 6, player: ''},
                {place: 7, player: ''},
                {place: 8, player: ''},
                {place: 9, player: ''}
        ]
    }
});


function sendPlay(value){

    var play = {place: value, player: player};

    var gameBoard = ticktacktoe.gameBoard;

    var filteredGameBoard = gameBoard.filter(function(e) {
        return e.payer == '';
    });
    var game = {};
    game.gameBoard = {};
    game.gameBoard.boardSet = filteredGameBoard;
    game.play = play;
    var data = JSON.stringify(game);

    $.ajax({
        type: 'post',
        url: '/ticktacktoe/play',
        data: JSON.stringify(game),
        contentType: "application/json; charset=utf-8",
        traditional: true,
        success: function (data) {
            resetBoard(data);
        }
    });
}
function resetBoard(jsonData) {

    for(var jsonPlay in jsonData.gameBoard.boardSet) {
        var play = $.grep(ticktacktoe.gameBoard, function(e) {
        return e.place == jsonPlay
        });
        if(play.length == 1) {
            play[0].player = jsonData.gameBoard.boardSet[jsonPlay].player;
        }
    }
}

$("input:radio[name=player]").click(function() {
     player = $(this).val();
    $("input:radio[player]").attr('disabled', true);
});


</script>