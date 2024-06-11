const form = document.getElementById("form-cadastro");

function validaNome(nomeCompleto){
    const nomeComoArray = nomeCompleto.split(' ');
    return nomeComoArray.length >= 2;
}

form.addEventListener('submit', function(evento){
    evento.preventDefault();

    const nomeFuncionario = document.getElementById('nomeCompleto');
    if (!validaNome(nomeFuncionario.value)){
        alert("O nome não está completo");
    } else{
        alert("tudo certo");
    }
})

console.log(form);
