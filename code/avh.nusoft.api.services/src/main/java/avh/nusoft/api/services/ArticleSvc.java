package avh.nusoft.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import avh.nusoft.api.common.NusoftConstants;
import avh.nusoft.api.model.Article;
import avh.nusoft.api.services.impl.ArticleSvcImpl;

import avh.nusoft.api.services.model.in.APIArticleIn;
import avh.nusoft.api.services.model.out.APIArticleOut;
import avh.nusoft.api.services.model.transformer.ModelTransformer;

@RestController
public class ArticleSvc {
	@Autowired private ArticleSvcImpl artSvc;

	@PreAuthorize("hasAnyRole('User')")
	@PostMapping(NusoftConstants.PrivateServletPath + "/article")
	public ResponseEntity<APIArticleOut> addArticle(@RequestBody APIArticleIn ai) {
		try {
			Article a = ModelTransformer.ArticleAPI2Model(ai);
			a = artSvc.addNewArticle(a, ai.getEmail());
			APIArticleOut res = ModelTransformer.ArticleModel2API(a);
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

	@PreAuthorize("hasAnyRole('User')")
	@DeleteMapping(NusoftConstants.PrivateServletPath + "/article/delete/{id}")
	public ResponseEntity<Boolean> deleteArticle(@PathVariable("id") String id) {
		try {
			boolean res = artSvc.deleteArticle(id);
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

	@PreAuthorize("hasAnyRole('User')")
	@PatchMapping(NusoftConstants.PrivateServletPath + "/article/update/{id}")
	public ResponseEntity<APIArticleOut> updateArticle(@PathVariable String id, @RequestBody APIArticleIn ai) {
		try {
			Article art = ModelTransformer.ArticleAPI2Model(ai);
			Article updatedArticle = artSvc.updateArticle(id, art);
			APIArticleOut res = ModelTransformer.ArticleModel2API(updatedArticle);
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}

	}

	@PreAuthorize("hasAnyRole('User')")
	@GetMapping(NusoftConstants.PrivateServletPath + "/article/{id}")
	public ResponseEntity<APIArticleOut> findArticleById(@PathVariable String id) {
		try {
			Article a = artSvc.getArticle(id);
			APIArticleOut res = ModelTransformer.ArticleModel2API(a);
			
			return ResponseEntity.ok().body(res);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}

	}

	@PreAuthorize("hasAnyRole('User')")
	@GetMapping(NusoftConstants.PrivateServletPath + "/article/all")
	public ResponseEntity<List<APIArticleOut>> getAllArticle(@RequestParam String email) {
		try {
			List<Article> arts = artSvc.getAllArticle(email);
			if (arts == null)
				return null;
			
			List<APIArticleOut> ap = new ArrayList<>();
			for (Article de : arts) {
				APIArticleOut res = ModelTransformer.ArticleModel2API(de);
				ap.add(res);
			}
			return ResponseEntity.ok().body(ap);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}
}
