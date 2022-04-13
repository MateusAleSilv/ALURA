module "aws-prod" {
  source = "../../infra"
  instacia = "t2.micro"
  regiao_aws = "us-east-1"
  chave = "IaC-PROD"
  grupoDeSeguranca = "Producao"
  minimo = 1
  maximo = 10
  nomeGrupo = "PROD"
  producao = true
}

