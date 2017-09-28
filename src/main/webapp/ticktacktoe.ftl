<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello Kotlin</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://unpkg.com/vue"></script>
    <style>
        td {height: 60px; width: 60px; padding: 0px; margin: 0px; text-align:center;}
        tr {padding: 0px; margin: 0px; }

        .green {background-color: #2ddf27;
                 -webkit-transition: background-color 1000ms linear;
                 -ms-transition: background-color 1000ms linear;
                   transition: background-color 1000ms linear;
                   }
        .red {background-color: #ff3339;
                 -webkit-transition: background-color 1000ms linear;
                 -ms-transition: background-color 1000ms linear;
                  transition: background-color 1000ms linear;
                  }

    </style>
</head>

<h1>Tick Tack Toe</h1>
<p>The rules are simple, Pick a player and then pick a spot. Winner has three in a row on the board</p>

<div id="ticktacktoe">
    <table title="tick tack toe">

        <h2 v-if="gameOver">Winner: {{winner}}</h2>
        <h2 v-else></h2>

        <tr>

            <td v-bind:class="{green: gameBoard[0].player === 'O', red: gameBoard[0].player === 'X'}" style="border-right:solid; border-bottom:solid" onclick="sendPlay(1)">{{gameBoard[0].player}}</td>

            <td v-bind:class="{green: gameBoard[1].player === 'O', red: gameBoard[1].player === 'X'}" style="border-bottom:solid; border-left:solid; border-right:solid" onclick="sendPlay(2)">
                {{gameBoard[1].player}}
            </td>
            <td v-bind:class="{green: gameBoard[2].player === 'O', red: gameBoard[2].player === 'X'}" style="border-left:solid; border-bottom:solid" onclick="sendPlay(3)">{{gameBoard[2].player}}</td>
        </tr>
        <tr>
            <td v-bind:class="{green: gameBoard[3].player === 'O', red: gameBoard[3].player === 'X'}" style="border-right:solid; border-bottom:solid; border-top:solid" onclick="sendPlay(4)">
                {{gameBoard[3].player}}
            </td>
            <td v-bind:class="{green: gameBoard[4].player === 'O', red: gameBoard[4].player === 'X'}" style="border-bottom:solid; border-left:solid; border-right:solid; border-top:solid"
                onclick="sendPlay(5)">{{gameBoard[4].player}}
            </td>
            <td v-bind:class="{green: gameBoard[5].player === 'O', red: gameBoard[5].player === 'X'}" style="border-left:solid; border-top:solid; border-bottom:solid" onclick="sendPlay(6)">
                {{gameBoard[5].player}}
            </td>
        </tr>
        <tr>
            <td v-bind:class="{green: gameBoard[6].player === 'O', red: gameBoard[6].player === 'X'}" style="border-right:solid; border-top:solid" onclick="sendPlay(7)">{{gameBoard[6].player}}</td>
            <td v-bind:class="{green: gameBoard[7].player === 'O', red: gameBoard[7].player === 'X'}" style="border-top:solid; border-left:solid; border-right:solid" onclick="sendPlay(8)">
                {{gameBoard[7].player}}
            </td>
            <td v-bind:class="{green: gameBoard[8].player === 'O', red: gameBoard[8].player === 'X'}" style="border-left:solid; border-top:solid" onclick="sendPlay(9)">{{gameBoard[8].player}}</td>
        </tr>
    </table>
</div>
<p>Choose X or O: </p>
<input type="radio" name="player" value="O">O</input>
<input type="radio" name="player" value="X">X</input>
<input type="button" value="Play Again" onclick="resetGame()">
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
                {place: 7, player: ''},
                {place: 8, player: ''},
                {place: 9, player: ''}
        ],
        gameOver: false,
        winner: ''
    }
});


function sendPlay(value){
    if( !ticktacktoe.gameOver) {
        var play = {place: value, player: player};

        var gameBoard = ticktacktoe.gameBoard;

        var filteredGameBoard = gameBoard.filter(function(e) {
            return e.player === 'O' || e.player === 'X';
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
}
function resetBoard(jsonData) {

     jsonData.gameBoard.boardSet.forEach(function(item) {
         var play = $.grep(ticktacktoe.gameBoard, function(play) {
            return play.place == item.place;
         });
         if(play[0] !== 'undefined') {
            play[0].player = item.player;
         } else {
            ticktacktoe.gameOver = true;
            ticktacktoe.winner = 'No Winner, Tied Game';
         }

     });

    if( jsonData.gameBoard.gameOver == true ) {
        ticktacktoe.gameOver = true;
        ticktacktoe.winner = jsonData.gameBoard.winner;
    }
}

$("input:radio[name=player]").click(function() {
     if( !ticktacktoe.gameOver ) {
        player = $(this).val();
        $("input:radio[name=player]").attr('disabled', true);
     }

});

function resetGame() {
    ticktacktoe.gameBoard.forEach(function (play) {
        play.player = '';
    });
    ticktacktoe.gameOver = false;
    ticktacktoe.winner = '';
}

</script>