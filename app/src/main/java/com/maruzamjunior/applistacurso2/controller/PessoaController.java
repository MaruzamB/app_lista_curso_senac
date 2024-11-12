package com.maruzamjunior.applistacurso2.controller;

import android.util.Log;

import com.maruzamjunior.applistacurso2.model.Pessoa;

public class PessoaController {

    @Override
    public String toString() {

        Log.d("MVC_CONTROLLER","CONTROLLER INICIADA");

        return "PessoaController{}";
    }

    public void salvar(Pessoa pessoa) {

        Log.d("MVC_CONTROLLER","DADOS SALVOS: " + pessoa.toString());
    }
}
