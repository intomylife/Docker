#!/bin/bash
## $() - 方法，${} - 变量

## 停止服务
docker-compose -f docker-compose.yaml -f docker-compose-all.yaml down
