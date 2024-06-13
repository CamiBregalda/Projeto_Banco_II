const form = document.getElementById("form-cadastro");

const nomeFuncionario = document.getElementById('nomeCompleto');
const cpfFuncionario = document.getElementById('cpf-funcionario');
const senhaFuncionario = document.getElementById('senha');
const cargoFuncionario = document.getElementById('cargo');


function cadastrar (){

    fetch("", {

        headers: {
            'Accept': 'aplication/json',
            'Content-Type': 'application/json'
        },
        method: "POST",

        body: JSON.stringify({
            nome: nomeFuncionario.value,
            cpf: cpfFuncionario.value,
            senha: senhaFuncionario.value, 
            cargo: cargoFuncionario.value
        })
    })

    .then(function(res) { console.log(res) })
    .catch(function(res){ console.log(res) });

};

let nomeEValido = false;

// ver se o nome está completo
function validaNome(nomeFuncionario){
    const nomeComoArray = nomeFuncionario.value.split(' ');
    return nomeComoArray.length >= 2;
}



form.addEventListener('submit', function(evento){
    evento.preventDefault();

    nomeEValido = validaNome(nomeFuncionario);

    if (nomeEValido){
        cadastrar();
    }


})

