<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello Kotlin</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://unpkg.com/vue"></script>
</head>
<body>
<h2>Hello Kotlin</h2>

<div id="app">
    <p v-if="loaded">{{json.message}}</p>
</div>

<script type="text/javascript">
var app = new Vue({
    el: '#app',
    data: {
        json: null,
        loaded: false
    }
});
$.getJSON('http://localhost:8181/hello', function (json) {
    app.json = json;
    app.loaded = true;
});
</script>

</body>
</html>