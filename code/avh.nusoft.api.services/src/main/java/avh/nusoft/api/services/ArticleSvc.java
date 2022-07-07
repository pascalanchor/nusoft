package avh.nusoft.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import avh.nusoft.api.model.Article;
import avh.nusoft.api.model.Degree;
import avh.nusoft.api.model.reps.NusoftRep;
import avh.nusoft.api.services.impl.ArticleSvcImp;

import avh.nusoft.api.services.model.in.APIArticleIn;
import avh.nusoft.api.services.model.in.APIDegreeIn;
import avh.nusoft.api.services.model.out.APIArticleOut;
import avh.nusoft.api.services.model.out.APIDegreeOut;
import avh.nusoft.api.services.transformer.ArticleTransformer;
import avh.nusoft.api.services.transformer.Transformer;

@RestController
public class ArticleSvc {
	@Autowired
	private ArticleSvcImp artSvc;
	@Autowired
	private NusoftRep rep;

	@PreAuthorize("hasAnyRole('User')")
	@PostMapping("/avh/nusoft/api/authorized/article")
	public ResponseEntity<APIArticleOut> addArticle(@RequestBody APIArticleIn ai) {
		try {
			Article a = ArticleTransformer.ArticleAPI2Model(ai);
			a = artSvc.addNewArticle(a, ai.getEmail());
			APIArticleOut res = ArticleTransformer.ArticleModel2API(a);
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

	@PreAuthorize("hasAnyRole('User')")
	@DeleteMapping("avh/nusoft/api/authorized/delete/article/{id}")
	public void deleteArticle(@PathVariable("id") String id) {
		rep.getArticleRep().deleteById(id);

	}

	@PreAuthorize("hasAnyRole('User')")
	@PutMapping("avh/nusoft/api/authorized/edit/article")
	public ResponseEntity<APIArticleOut> editArticle(@RequestParam String id, @RequestBody APIArticleIn ai) {
		try {
			Article a = rep.getArticleRep().findByEid(id);
			a.setArticleDate(ai.getDate());
			a.setDescription(ai.getDescription());
			a.setTitle(ai.getTile());
			a.setUrlLink(ai.getUrl());
			rep.getArticleRep().save(a);

			APIArticleOut res = ArticleTransformer.ArticleModel2API(a);
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}

	}

	@PreAuthorize("hasAnyRole('User')")
	@GetMapping("avh/nusoft/api/authorized/get/article/id")
	public ResponseEntity<APIArticleOut> findArticleById(@RequestParam String id) {
		try {
			Article a = rep.getArticleRep().findByEid(id);
			APIArticleOut res = ArticleTransformer.ArticleModel2API(a);
			return ResponseEntity.ok().body(res);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}

	}

	@PreAuthorize("hasAnyRole('User')")
	@GetMapping("avh/nusoft/api/authorized/all/article/contact")
	public List<APIArticleOut> getArticle(@RequestParam String email) {
		try {
			List<Article> a = artSvc.getArticle(email);
			List<APIArticleOut> ap = new ArrayList<>();
			for (Article de : a) {
				APIArticleOut res = ArticleTransformer.ArticleModel2API(de);
				ap.add(res);
			}
			return ap;
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

}
