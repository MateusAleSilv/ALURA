terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 3.27"
    }
  }

  required_version = ">= 0.14.9"
}

provider "aws" {
  profile = "default"
  region  = "us-east-1"
}

resource "aws_instance" "app_server" {
  ami           = "ami-04505e74c0741db8d"
  instance_type = "t2.micro"
  key_name = "admin-key"
# user_data = <<-EOF
   #              #!/bin/bash
   #              cd /home/ubunto
   #              echo "<h1>Feito com Terraform</h1>" > index.html
   #              nohup busybox httpd -f -p 8080 &
   #              EOF
  tags = {
    Name = "Terraform Ansible Python"
  }
}
