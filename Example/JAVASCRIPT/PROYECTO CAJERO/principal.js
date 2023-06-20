import iden_user from "./user.js";
var balance_debit = function formula() {
  var cantidadT = parseInt(document.getElementById("Cant").value);
  var Cantidad1 = parseInt(document.getElementById("C1").value);
  var Cantidad2 = parseInt(document.getElementById("C2").value);
  document.getElementById("C1").value = Cantidad1 - cantidadT;
  document.getElementById("C2").value = Cantidad2 + cantidadT;
};
function formula2() {
  var cantidadT = parseInt(document.getElementById("Cant").value);
  var Cantidad1 = parseInt(document.getElementById("C1").value);
  var Cantidad2 = parseInt(document.getElementById("C2").value);
  document.getElementById("C1").value = Cantidad1 + cantidadT;
  document.getElementById("C2").value = Cantidad2 - cantidadT;
}
function formula3() {
  var cantidadT = parseInt(document.getElementById("Cant").value);
  var Cantidad1 = parseInt(document.getElementById("C1").value);
  document.getElementById("C1").value = Cantidad1 + cantidadT;
}

function formula4() {
  var cantidadT = parseInt(document.getElementById("Cant").value);
  var Cantidad2 = parseInt(document.getElementById("C2").value);
  document.getElementById("C2").value = Cantidad2 + cantidadT;
}
function formula5() {
  var cantidadT = parseInt(document.getElementById("Cant").value);
  var Cantidad1 = parseInt(document.getElementById("C1").value);
  document.getElementById("C1").value = Cantidad1 - cantidadT;
}
function formula6() {
  var cantidadT = parseInt(document.getElementById("Cant").value);
  var Cantidad2 = parseInt(document.getElementById("C2").value);
  document.getElementById("C2").value = Cantidad2 - cantidadT;
}
