package com.aisa.mpp.api.mppapi.service.post;

import com.aisa.mpp.api.mppapi.model.post.Post;
import com.aisa.mpp.api.mppapi.repository.post.PostRepository;
import com.aisa.mpp.api.mppapi.utils.SimilarityUtils;
import com.aisa.mpp.api.mppapi.utils.TextUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * NLP - Text tokenization, stemming, and cosine similarity algorithm to find relevant posts for global search
 * Need to Amend later for all type of posts
 */

@Service
public class SearchService {

    private final PostRepository postRepository;

    public SearchService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> searchPosts(String query) {
        List<String> queryTokens = TextUtils.tokenizeAndStem(query);

        return postRepository.findAll().stream()
                .filter(post -> {
                    List<String> titleTokens = TextUtils.tokenizeAndStem(post.getTitle());
                    List<String> descTokens = TextUtils.tokenizeAndStem(post.getDescription());

                    Map<String, Integer> queryVector = SimilarityUtils.vectorize(queryTokens);
                    Map<String, Integer> titleVector = SimilarityUtils.vectorize(titleTokens);
                    Map<String, Integer> descVector = SimilarityUtils.vectorize(descTokens);

                    double titleSimilarity = SimilarityUtils.cosineSimilarity(queryVector, titleVector);
                    double descSimilarity = SimilarityUtils.cosineSimilarity(queryVector, descVector);

                    // Threshold for similarity
                    return titleSimilarity > 0.2 || descSimilarity > 0.2;
                })
                .collect(Collectors.toList());
    }

    public Map<String, List<Post>> searchPostsGroupedByCategory(String query) {
        List<String> queryTokens = TextUtils.tokenizeAndStem(query);

        List<Post> filteredPosts = postRepository.findAll().stream()
                .filter(post -> {
                    List<String> titleTokens = TextUtils.tokenizeAndStem(post.getTitle());
                    List<String> descTokens = TextUtils.tokenizeAndStem(post.getDescription());

                    Map<String, Integer> queryVector = SimilarityUtils.vectorize(queryTokens);
                    Map<String, Integer> titleVector = SimilarityUtils.vectorize(titleTokens);
                    Map<String, Integer> descVector = SimilarityUtils.vectorize(descTokens);

                    double titleSimilarity = SimilarityUtils.cosineSimilarity(queryVector, titleVector);
                    double descSimilarity = SimilarityUtils.cosineSimilarity(queryVector, descVector);

                    // Threshold for similarity
                    return titleSimilarity > 0.2 || descSimilarity > 0.2;
                })
                .collect(Collectors.toList());

        // Grouping posts by category
        return filteredPosts.stream()
                .collect(Collectors.groupingBy(Post::getCategory));
    }
}
