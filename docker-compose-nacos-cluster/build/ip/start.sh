#!/bin/bash
## $() - 方法，${} - 变量

## 获取入参，启动类型
start_type=$1
## 输出看一眼
echo start_type: ${start_type}

## 参数校验
if [ "$start_type" == "discovery" ]||[ "$start_type" == "config" ]||[ "$start_type" == "all" ];
then
    ## 先启动基础服务
    docker-compose -f docker-compose.yaml up -d

    ## 循环十次检查基础服务是否启动完成
    for((i=1;i<=10;i++));
    do
        ## 检查服务是否启动完成
        code=$(curl -o /dev/null -s -w %{http_code} 127.0.0.1:9000/nacos)
        if [ ${code} == '302' ];
        then
            echo 'check  ---  success  --- ' ${code};
            break;
        else
            echo 'check  ---  loading  --- ' ${code};
        fi
        ## 三秒中检查一次
        sleep 3;
    done

    ## 检查结束或检查终止，再次获取服务状态码
    code=$(curl -o /dev/null -s -w %{http_code} 127.0.0.1:9000/nacos)
    if [ ${code} == '302' ];
    then
        ## 启动完成，接着启动后端应用服务
        docker-compose -f docker-compose-$start_type.yaml up -d
        echo 'success';
    else
        ## 启动失败，可能出错了，停止基础服务
        docker-compose -f docker-compose.yaml down
        echo 'fail';
    fi
else
    ## 参数有误
    echo 'end';
fi

