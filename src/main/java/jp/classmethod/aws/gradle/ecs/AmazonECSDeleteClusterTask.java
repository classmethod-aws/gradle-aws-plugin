/*
 * Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.classmethod.aws.gradle.ecs;

import lombok.Getter;
import lombok.Setter;

import org.gradle.api.GradleException;
import org.gradle.api.internal.ConventionTask;
import org.gradle.api.tasks.TaskAction;

import com.amazonaws.services.ecs.AmazonECS;
import com.amazonaws.services.ecs.model.DeleteClusterRequest;
import com.amazonaws.services.ecs.model.DeleteClusterResult;

public class AmazonECSDeleteClusterTask extends ConventionTask {
	
	@Getter
	@Setter
	private String cluster;
	
	@Getter
	private DeleteClusterResult deleteClusterResult;
	
	
	public AmazonECSDeleteClusterTask() {
		setDescription("Delete Cluster Task.");
		setGroup("AWS");
	}
	
	@TaskAction
	public void deleteCluster() {
		// to enable conventionMappings feature
		String cluster = getCluster();
		
		if (cluster == null) {
			throw new GradleException("Cluster is required");
		}
		
		AmazonECSPluginExtension ext = getProject().getExtensions().getByType(AmazonECSPluginExtension.class);
		AmazonECS ecs = ext.getClient();
		
		DeleteClusterRequest request = new DeleteClusterRequest()
			.withCluster(cluster);
		
		deleteClusterResult = ecs.deleteCluster(request);
		
		String clusterArn = deleteClusterResult.getCluster().getClusterArn();
		getLogger().info("Delete ECS Cluster requested: {}", clusterArn);
	}
}
