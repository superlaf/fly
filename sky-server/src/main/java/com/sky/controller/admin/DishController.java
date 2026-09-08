package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/dish")
@Api(tags="通用接口")
public class DishController {
    @Autowired
    private DishService dishService;


    @PostMapping()
    @ApiOperation("新增菜品")
    public Result add(@RequestBody DishDTO dishDTO){
        log.info("新增菜品为:{}",dishDTO);
        dishService.add(dishDTO);
        return Result.success();
    }
    @GetMapping("/page")
    public Result<PageResult> page(@RequestBody DishPageQueryDTO dishPageQueryDTO){
        PageResult pageResult= dishService.page(dishPageQueryDTO);
        return Result.success(pageResult);

    }
    @DeleteMapping
    public Result delete(@RequestParam List<Long> ids){
        dishService.delete(ids);
     return Result.success();
    }
    @GetMapping("/{id}")
   public Result<DishVO> getById(@PathVariable Long id){
        DishVO dishVo=dishService.getById(id);
        return Result.success(dishVo);
   }
    public Result update(@RequestBody DishDTO dishDTO){
        dishService.update(dishDTO);
        return Result.success();
    }

}
