## 自动生成证书脚本
#!/bin/bash


## 全局变量
### 密码
PASSPHRASE="987654321"


echo "===================== 开始 ====================="


## 创建需要的目录
mkdir -p /root/ca/my
## 进入到目录
cd /root/ca/my


## 生成 ca 私钥，并设置密码
openssl genrsa -aes256 -passout pass:$PASSPHRASE -out ca-key.pem 4096
## 使用 ca 私钥创建 ca 证书
openssl req -new -x509 -sha256 -passin "pass:$PASSPHRASE" -days 365 -subj "/CN=*" -key ca-key.pem -out ca.pem


## 生成服务器私钥
openssl genrsa -out server-key.pem 4096
## 使用服务器私钥创建服务器证书
openssl req -new -sha256 -subj "/CN=*" -key server-key.pem -out server.csr

## 生成服务器自签证书
openssl x509 -req -days 365 -sha256 -in server.csr -passin "pass:$PASSPHRASE" -CA ca.pem -CAkey ca-key.pem -CAcreateserial -out server-cert.pem


## 生成客户端私钥
openssl genrsa -out key.pem 4096
## 使用客户端私钥创建客户端证书
openssl req -new -subj "/CN=client" -key key.pem -out client.csr

## 配置 extendedKeyUsage
sh -c 'echo extendedKeyUsage=clientAuth >> extfile.cnf'
## 生成客户端自签证书
openssl x509 -req -days 365 -sha256 -in client.csr -passin "pass:$PASSPHRASE" -CA ca.pem -CAkey ca-key.pem -CAcreateserial -extfile extfile.cnf -out cert.pem


## 删除多余文件
rm -rf ca.srl client.csr ipWhiteList.cnf server.csr extfile.cnf
## 更改密钥权限
chmod 0400 ca-key.pem server-cert.pem server-key.pem
chmod 0444 ca.pem key.pem cert.pem


echo "===================== 结束 ====================="