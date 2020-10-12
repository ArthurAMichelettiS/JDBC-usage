package com.comum;

public class DadosUsuario extends Entidade{
        private String senha;

        private String login;

        public int Id;

        public String Nome;

        public String Sobrenome;

        public int Numteste;
        /**
         * @return the senha
         */
        public String getSenha() {
            return senha;
        }

        /**
         * @param senha the senha to set
         */
        public void setSenha(String senha) {
            this.senha = senha;
        }

        public String getLogin() {
            return  login;
        }

        public void setLogin(String login) {
            this.login = login;
        }


    }


