/*
 * Copyright 2013-2017 Classmethod, Inc.
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

// -----------------------------------------------------------------------------
// Tasks related to Amazon EC2 Container Service.
//
// @author Dongjun Lee (chaz.epps@gmail.com)
// -----------------------------------------------------------------------------

package jp.classmethod.aws.gradle.ecs;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import jp.classmethod.aws.gradle.AwsPlugin;

public class AmazonECSPlugin implements Plugin<Project> {

	public void apply(Project project) {
		project.getPluginManager().apply(AwsPlugin.class);
		project.getExtensions().create(AmazonECSPluginExtension.NAME, AmazonECSPluginExtension.class, project);
	}
}
