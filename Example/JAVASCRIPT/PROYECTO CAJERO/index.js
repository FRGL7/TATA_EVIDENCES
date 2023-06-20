import iden_user from "./user.js";

function vadilation_user(user, contraseña) {
  var user_correct = false;
  var user_debit = 0;
  var user_credit = 0;
  var user = document.getElementById("name").value;
  var contraseña = document.getElementById("passw").value;

  for (let i = 0; i < iden_user.users.length; i++) {
    if (
      user == iden_user.users[i].user1 &&
      contraseña == iden_user.users[i].pass
    ) {
      user_correct = true;
      iden_user.user_index = i;
    }
  }

  return user_correct;
}

function login(form) {
  var validador = vadilation_user();

  if (validador === true) {
    location = "principal.html";
  } else {
    alert("AHMO CUALLI COPIXTLI");
  }
}
