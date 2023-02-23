package com.example.sqldemo.controller;

import com.example.sqldemo.bean.Result;
import com.example.sqldemo.exception.ParamsException;
import com.example.sqldemo.service.SqlService;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class SqlController {

    @Autowired
    SqlService sqlService;

    @PostMapping("/sql")
    public Result execute(@RequestBody Map data) throws ParamsException {
        System.out.println("sql:" + data.get("sql") + "type:" + data.get("type"));
        List<String> sqls = (List<String>) data.get("sql");
        String type = (String) data.get("type");
        if (sqls == null || sqls.size() == 0) throw new ParamsException();
        Object[] res = "mysql".equals(type) ? sqlService.execute(sqls) : sqlService.execute(sqls, type);
        return new Result<>(200, "success", res);
    }

}
