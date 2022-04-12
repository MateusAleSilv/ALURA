module "aws-prod" {
  source = "../../infra"
  instacia = "t2.micro"
  regiao_aws = "us-east-1"
  chave = "IaC-PROD"
}

output "IP" {
  value = module.aws-prod.IP_publico
}