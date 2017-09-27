var blockTypes = [
    "Core", "Wire", "Button", "Not", "Or", "And", "Xor", "Cross", "Light"
];

var orientations = [
    "top", "right", "bottom", "left"
];

var SIDE_ALL = -1;
var SIDE_TOP = 0;
var SIDE_RIGHT = 1;
var SIDE_BOTTOM = 2;
var SIDE_LEFT = 3;


function createBlock(block) {
    switch(blockTypes[block.blockType]) {
        case "Wire":
            return createWire(block);
        case "Cross":
            return createCross(block);
        default:
            return createLogic(block);
    }
}

function createWire(block) {
    var wire = document.createElement("div");
    wire.className = "block wire";
    wire.setAttribute("data-orientation", orientations[block.orientation])

    if (block.powered)
        wire.classList.add("powered");


    if (block.adjacentPowerStates[SIDE_TOP] != null)
        wire.innerHTML += '<div class="top"></div>';
    if (block.adjacentPowerStates[SIDE_RIGHT] != null)
        wire.innerHTML += '<div class="right"></div>';
    if (block.adjacentPowerStates[SIDE_BOTTOM] != null)
        wire.innerHTML += '<div class="bottom"></div>';
    if (block.adjacentPowerStates[SIDE_LEFT] != null)
        wire.innerHTML += '<div class="left"></div>';


    if(block.adjacentPowerStates[SIDE_TOP] == null &&
        block.adjacentPowerStates[SIDE_RIGHT] == null &&
        block.adjacentPowerStates[SIDE_BOTTOM] == null &&
        block.adjacentPowerStates[SIDE_LEFT] == null) {


        wire.innerHTML += '<div class="top"></div>';
        wire.innerHTML += '<div class="right"></div>';
        wire.innerHTML += '<div class="bottom"></div>';
        wire.innerHTML += '<div class="left"></div>';
    }


    return wire;
}

function createCross(block) {
    var wire = document.createElement("div");
    wire.className = "block cross";
    wire.setAttribute("data-orientation", orientations[block.orientation])


    if(coalesce(block.adjacentPowerStates[SIDE_TOP], false) ||
        coalesce(block.adjacentPowerStates[SIDE_BOTTOM], false)) {

        wire.innerHTML += '<div class="top powered"></div>';
        wire.innerHTML += '<div class="bottom powered"></div>';
    } else {
        wire.innerHTML += '<div class="top"></div>';
        wire.innerHTML += '<div class="bottom"></div>';
    }

    if(coalesce(block.adjacentPowerStates[SIDE_LEFT], false) ||
        coalesce(block.adjacentPowerStates[SIDE_RIGHT], false)) {

        wire.innerHTML += '<div class="left powered"></div>';
        wire.innerHTML += '<div class="right powered"></div>';
    } else {
        wire.innerHTML += '<div class="left"></div>';
        wire.innerHTML += '<div class="right"></div>';
    }


    return wire;
}

function createLogic(block) {
    var blockType = blockTypes[block.blockType];

    var logic = document.createElement("div");
    logic.className = "block logic " + blockType;
    logic.setAttribute("data-orientation", orientations[block.orientation])

    if (block.powered)
        logic.classList.add("powered");

    if (block.adjacentPowerStates[SIDE_TOP] != null) {
        var wire = document.createElement("div");
        wire.classList.add("top");

        if (block.adjacentPowerStates[SIDE_TOP] == true)
            wire.classList.add("powered");
        else if (block.orientation == SIDE_TOP && block.powered)
            wire.classList.add("powered");

        logic.appendChild(wire);
    }
    if (block.adjacentPowerStates[SIDE_RIGHT] != null) {
        var wire = document.createElement("div");
        wire.classList.add("right");

        if (block.adjacentPowerStates[SIDE_RIGHT] == true)
            wire.classList.add("powered");
        else if (block.orientation == SIDE_RIGHT && block.powered)
            wire.classList.add("powered");

        logic.appendChild(wire);
    }
    if (block.adjacentPowerStates[SIDE_BOTTOM] != null) {
        var wire = document.createElement("div");
        wire.classList.add("bottom");

        if (block.adjacentPowerStates[SIDE_BOTTOM] == true)
            wire.classList.add("powered");
        else if (block.orientation == SIDE_BOTTOM && block.powered)
            wire.classList.add("powered");

        logic.appendChild(wire);
    }
    if (block.adjacentPowerStates[SIDE_LEFT] != null) {
        var wire = document.createElement("div");
        wire.classList.add("left");

        if (block.adjacentPowerStates[SIDE_LEFT] == true)
            wire.classList.add("powered");
        else if (block.orientation == SIDE_LEFT && block.powered)
            wire.classList.add("powered");

        logic.appendChild(wire);
    }

    var imgSrc = "";
    switch(blockType) {
        case "And":
            imgSrc = "AndGate.png";
            break;
        case "Not":
            imgSrc = "NotGate.png";
            break;
        case "Or":
            imgSrc = "OrGate.png";
            break;
        case "Xor":
            imgSrc = "XorGate.png";
            break;
        case "Button":
            if(block.powered)
                imgSrc = "ButtonOn.png";
            else
                imgSrc = "ButtonOff.png";
            break;
        case "Light":
            if(block.powered)
                imgSrc = "LightOn.png";
            else
                imgSrc = "LightOff.png";
            break;
    }

    logic.innerHTML += '<img src="images/' + imgSrc + '" />';

    return logic;
}


function coalesce(value, defaultValue) {
    if(value == null)
        return defaultValue;
    return value;
}