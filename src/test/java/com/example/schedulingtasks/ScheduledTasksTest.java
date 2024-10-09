/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.schedulingtasks;

import org.awaitility.Durations;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ScheduledTasksTest {

	@SpyBean
	ScheduledTasks tasks;

	@Test
	public void reportCurrentTime() {
		// Utiliza Awaitility para esperar un máximo de 10 segundos hasta que se cumpla la condición especificada
		// en el bloque untilAsserted. Esto es útil en escenarios asíncronos donde no se puede predecir el
		// momento exacto en el que ocurrirá la acción.
		await().atMost(Durations.TEN_SECONDS).untilAsserted(() -> {
			// Verifica que el método reportCurrentTime() de la clase ScheduledTasks haya sido llamado
			//  al menos dos veces en los 10 segundos establecidos.
			//  Esto es típico para probar que una tarea programada se ejecuta con la frecuencia esperada.
			verify(tasks, atLeast(2)).reportCurrentTime();
		});
	}
}
