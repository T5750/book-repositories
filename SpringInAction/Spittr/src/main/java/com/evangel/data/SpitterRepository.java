package com.evangel.data;

import com.evangel.Spitter;

public interface SpitterRepository {
	Spitter save(Spitter spitter);

	Spitter findByUsername(String username);
}
