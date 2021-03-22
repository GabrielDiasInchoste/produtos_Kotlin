package com.gabrieldias.produtos.controller

import com.gabrieldias.produtos.entity.ProdutoEntity
import com.gabrieldias.produtos.service.ProdutoService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import javax.validation.Valid

@RestController
@RequestMapping("/v1/")
class ProdutoController (
      private val produtoService : ProdutoService
      ){


    @GetMapping(value = ["produto/{produtoId}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getProduto(@PathVariable(value = "produtoId") produtoId: Long): ResponseEntity<ProdutoEntity?>? {
        return ResponseEntity.status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(produtoService.getProduto(produtoId))
    }
    
}