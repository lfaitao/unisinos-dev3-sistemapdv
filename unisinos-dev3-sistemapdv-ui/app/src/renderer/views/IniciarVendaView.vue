<template>
    <div>
        <navbar title="Iniciar Venda" previousPage="/"></navbar>
        <div class="centered_div">
            <md-layout md-align="center">
                <md-layout style="min-width:300px">         
                    <md-input-container v-bind:class="{ 'md-input-invalid': isValid === false }">
                        <label>Informe o CPF</label>
                        <md-input required v-model="cpf"></md-input>
                        <span v-show="!isValid" class="md-error">CPF inválido ou inexistente</span>
                    </md-input-container>  
                    
                    <md-button class="md-raised md-primary" @click.native="goTo({path:'/mesclar-pre-venda', query: {cpf:cpf}})">Iniciar Venda</md-button>
                </md-layout>
            </md-layout>
        </div>
    </div>
</template>

<script>
  import {router} from '../main'

  export default {
      data() {
          return {
              cpf: null,
              isValid: true,
              title: 'Iniciar Venda'
          }
      },
      methods: {
          goTo(route) {
              console.log(this.cpf);
              this.isValid = this.cpfIsValid(this.cpf);

              if(this.isValid)
                router.push(route);
          },
          cpfIsValid(cpf) {  
                cpf = cpf.replace(/[^\d]+/g,'');    
                if(cpf == '') return false; 
                // Elimina CPFs invalidos conhecidos    
                if (cpf.length != 11 || 
                    cpf == "00000000000" || 
                    cpf == "11111111111" || 
                    cpf == "22222222222" || 
                    cpf == "33333333333" || 
                    cpf == "44444444444" || 
                    cpf == "55555555555" || 
                    cpf == "66666666666" || 
                    cpf == "77777777777" || 
                    cpf == "88888888888" || 
                    cpf == "99999999999")
                        return false;       
                // Valida 1o digito 
                let add = 0;    
                for (let i=0; i < 9; i ++)       
                    add += parseInt(cpf.charAt(i)) * (10 - i);  
                    let rev = 11 - (add % 11);  
                    if (rev == 10 || rev == 11)     
                        rev = 0;    
                    if (rev != parseInt(cpf.charAt(9)))     
                        return false;       
                // Valida 2o digito 
                add = 0;    
                for (let i = 0; i < 10; i ++)        
                    add += parseInt(cpf.charAt(i)) * (11 - i);  
                rev = 11 - (add % 11);  
                if (rev == 10 || rev == 11) 
                    rev = 0;    
                if (rev != parseInt(cpf.charAt(10)))
                    return false;       
                return true;   
            }
      },
      mounted() {
      },
      name: 'iniciar-venda'
  }
</script>

<style scoped>
    .centered_div {
        position: absolute;
        top:  22%;
        bottom:  50%;
        left: 50%;
        right: 50%;
    }
</style>
