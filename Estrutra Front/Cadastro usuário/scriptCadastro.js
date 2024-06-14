const formulario = document.querySelector("form");

const nomeCompletoUsuario = document.getElementById('nomeCompleto');
const nomeUsuario = document.getElementById('nomeUsuario');
const cpfUsuario = document.getElementById('cpfUsuario');
const senhaUsuario = document.getElementById('senhaUsuario');
const confirmaSenha = document.getElementById('confirmaSenha');


//contato com o back
function cadastrar (){
    
    fetch("http://localhost:8080/users/cadastrar", {

        headers: {
            'Content-Type': 'application/json'
        },
        method: "POST",

        body: JSON.stringify({
            userRequest: {
                user_nome: nomeCompletoUsuario.value,
                user_cpf: cpfUsuario.value,
                user_username: nomeUsuario.value,
                user_senha: senhaUsuario.value
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
            throw new Error("Erro ao cadastrar usuário");
        }
    })
    .then(data => {
        console.log("Usuário cadastrado com sucesso:", data);
    })
    .catch(error => {
        console.error("Erro na requisição", error);
    });

};

/*function limpar() {
    nomeCompletoUsuario.value = "";
    nomeUsuario.value = "";
    cpfUsuario.value = "";
    senhaUsuario.value = "";
}*/


let nomeEValido, senhaEValida = false;

// ver se o nome é válido
function validaNome(nomeCompletoUsuario){
    const nomeComoArray = nomeCompletoUsuario.value.split(' ');
    return nomeComoArray.length >= 2;
}

//ver se as senhas são iguais
function SenhasIguais(senhaUsuario, confirmaSenha){
    if (senhaUsuario.value == confirmaSenha.value){
        return true;
    }
}


formulario.addEventListener('submit', function(evento){
    evento.preventDefault();

    nomeEValido = validaNome(nomeCompletoUsuario);
    senhaEValida = SenhasIguais(senhaUsuario, confirmaSenha);

    if(nomeEValido && senhaEValida){
        cadastrar();
        //mandar para a página inicial
    
        //limpar();
    } else if (!nomeEValido && senhaEValida){
        //avisar que o nome está incompleto;
        
    } else if (nomeEValido && !senhaEValida){
        //avisar que as senhas não estão iguais
        
    }else{
        //mandar arrumar o nome e as senhas

    }

//display: none
    

})