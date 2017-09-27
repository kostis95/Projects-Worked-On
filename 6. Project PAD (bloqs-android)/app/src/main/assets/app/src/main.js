 var simulation;

var debug = false;

function onTickEvent(currentTick) {
    if(debug) {
        document.getElementById("grid").innerHTML = Android.getJson();
        return;
    }


    simulation = JSOG.parse(Android.getJson());
    document.getElementById("currentTick").innerHTML = simulation.currentTick;


    document.getElementById("grid").innerHTML = "";


    for(var y = 0; y < simulation.gridWidth; y++) {
        var gridRow = document.createElement("div");
        gridRow.className = "grid-row";

        for(var x = 0; x < simulation.gridHeight; x++){
            var ele = document.createElement("div");
            ele.className = "grid-item";
            ele.setAttribute("onclick", "Android.gridClick(" + x + ", " + y + ");");

            if (simulation.grid[x][y] != null) {
                ele.appendChild(createBlock(simulation.grid[x][y]));
            }

            gridRow.appendChild(ele);
        }

        document.getElementById("grid").appendChild(gridRow);
    }
}

