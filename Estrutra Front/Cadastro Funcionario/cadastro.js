const form = document.getElementById("form-cadastro");

const nomeFuncionario = document.getElementById('nomeCompleto');
const cpfFuncionario = document.getElementById('cpf-funcionario');
const senhaFuncionario = document.getElementById('senha');
const cargoFuncionario = document.getElementById('cargo');


function cadastrar (){

    fetch("http://localhost:8080/funcionarios/cadastrar", {

        headers: {
            'Content-Type': 'application/json'
        },
        method: "POST",

        body: JSON.stringify({
            funcionarioRequest: {
                fun_nome: nomeFuncionario.value,
                fun_cpf: cpfFuncionario.value,
                fun_senha: senhaFuncionario.value, 
                fun_funcao: cargoFuncionario.value
            },
            userLoginDTO: {
                username: "user_login",
                password: "user_senha" 
            }
            
        })
    })
    .then(res => {
        if (res.ok) {
            return res.json();
        } else {
            throw new Error("Erro ao cadastrar Funcionário");
        }
    })
    .then(data => {
        console.log("Funcionário cadastrado com sucesso:", data);
    })
    .catch(error => {
        console.error("Erro na requisição", error);
    });


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

